package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private static final long SERIAL_VERSION_UID = 1L;
    public static final ListSection EMPTY = new ListSection("");
    private List<String> item;

    public ListSection() {
    }

    public ListSection(String... item) {
        this(Arrays.asList(item));
    }

    public ListSection(List<String> item) {
        Objects.requireNonNull(item, "item must not be null");
        this.item = item;
    }

    public List<String> getItem() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return item.equals(that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "item=" + item +
                '}';
    }
}
