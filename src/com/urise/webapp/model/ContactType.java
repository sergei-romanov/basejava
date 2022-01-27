package com.urise.webapp.model;

public enum ContactType {
    PHONE("Phone"),
    EMAIL("Email"),
    MESSENGER("Messenger"),
    SKYPE("Skype"),
    LinkedIn("LinkedIn"),
    GITHUB("Github"),
    STACKOVERFLOW("Stack Overflow");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
