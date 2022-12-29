package com.urise.webapp.model;

import java.util.Date;
import java.util.Objects;

public class Organization {
    private final Link homePage;

    private final Date beginWorkDate;
    private final Date endWorkDate;
    private final String title;
    private final String description;

    public Organization(String name, String url, Date beginWorkDate, Date endWorkDate, String title, String description) {
        Objects.requireNonNull(beginWorkDate, "beginWorkDate must not be null");
        Objects.requireNonNull(endWorkDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.homePage = new Link(name, url);
        this.beginWorkDate = beginWorkDate;
        this.endWorkDate = endWorkDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && beginWorkDate.equals(that.beginWorkDate) && endWorkDate.equals(that.endWorkDate) && title.equals(that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, beginWorkDate, endWorkDate, title, description);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", beginWorkDate=" + beginWorkDate +
                ", endDate=" + endWorkDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
