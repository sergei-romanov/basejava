package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private final Link homePage;
    private final List<Position> position = new ArrayList<>();

    public Organization(String name, String url, Position position) {
        this.homePage = new Link(name, url);
        this.position.add(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        if (!homePage.equals(that.homePage)) return false;
        return position.equals(that.position);

    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", description='" + position + '\'' +
                '}';
    }
}
