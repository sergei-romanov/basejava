package com.urise.webapp.model;

import java.util.List;

public class InfoBlock implements Section {
    private final List<String> content;

    public InfoBlock(List<String> content) {
        this.content = content;
    }

    public List<String> getContent() {
        return content;
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
        return content.toString();
    }
}
