package com.urise.webapp.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private String fullName;

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume() {
        this(UUID.randomUUID().toString());
        this.fullName = "";
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        var arO = o.getFullName().split(" ");
        var lastNameO = arO[0];
        var firstNameO = arO[1];
        var arThis = this.getFullName().split(" ");
        var lastNameThis = arThis[0];
        var firstNameThis = arThis[1];
        if (lastNameThis.compareTo(lastNameO) > 0) {
            return 1;
        }
        if (lastNameThis.compareTo(lastNameO) == 0) {
            if (firstNameThis.compareTo(firstNameO) >= 0) {
                return 1;
            }
            return -1;
        }
        return -1;
    }
}