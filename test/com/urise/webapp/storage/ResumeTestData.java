package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ResumeTestData {
    private static Integer uuid = 1;

    public static Resume createResume(String uuid, String fullName) {
        return new Resume(uuid, fullName);
    }

    public static Resume createResume() {
        return new Resume(uuid.toString(), "name" + uuid++);
    }
}
