package com.urise.webapp.model;

public class Info implements Section {
    private final String content;

    public Info(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return content;
    }
}
