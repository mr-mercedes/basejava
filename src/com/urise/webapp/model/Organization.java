package com.urise.webapp.model;

import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final String title;
    private final String description;

    public Organization(String name, String url, String title, String description) {
        Objects.requireNonNull(title, "title must not be null");
        this.homePage = new Link(name, url);
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && title.equals(that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, title, description);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
