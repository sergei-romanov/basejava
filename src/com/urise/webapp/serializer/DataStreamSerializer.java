package com.urise.webapp.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                final SectionType sectionType = entry.getKey();
                final Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(section.toString());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        ListSection ls = (ListSection) section;
                        dos.writeInt(ls.getItems().size());
                        for (String s : ls.getItems()) {
                            dos.writeUTF(s);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        OrganizationSection org = (OrganizationSection) section;
                        dos.writeInt(org.getOrganizations().size());
                        for (Organization organization : org.getOrganizations()) {
                            var homePage = organization.getHomePage();
                            dos.writeUTF(homePage.getName());
                            dos.writeUTF(homePage.getUrl());
                            var positions = organization.getPositions();
                            dos.writeInt(positions.size());
                            for (Organization.Position s : positions) {
                                dos.writeInt(s.getStartDate().getYear());
                                dos.writeInt(s.getStartDate().getMonth().getValue());
                                dos.writeInt(s.getStartDate().getDayOfMonth());
                                dos.writeInt(s.getEndDate().getYear());
                                dos.writeInt(s.getEndDate().getMonth().getValue());
                                dos.writeInt(s.getEndDate().getDayOfMonth());
                                dos.writeUTF(s.getTitle());
                                dos.writeUTF(s.getDescription());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            var sizeContacts = dis.readInt();
            for (var i = 0; i < sizeContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            var sizeSections = dis.readInt();
            for (var i = 0; i < sizeSections; i++) {
                var sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> resume.addSection(sectionType, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> list = new ArrayList<>();
                        var size = dis.readInt();
                        for (var j = 0; j < size; j++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(list));
                    }
                    case EXPERIENCE, EDUCATION -> {
                        var list = new ArrayList<Organization>();
                        var sizeOrganizationList = dis.readInt();
                        for (var j = 0; j < sizeOrganizationList; j++) {
                            var link = new Link(dis.readUTF(), dis.readUTF());
                            var sizePositionsList = dis.readInt();
                            var positions = new ArrayList<Organization.Position>();
                            for (var k = 0; k < sizePositionsList; k++) {
                                positions.add(new Organization.Position(
                                        LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt()),
                                        LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt()),
                                        dis.readUTF(),
                                        dis.readUTF()));
                            }
                            Organization organization = new Organization(link, positions);
                            list.add(organization);
                        }
                        resume.addSection(sectionType, new OrganizationSection(list));
                    }
                }
            }
            return resume;
        }
    }
}