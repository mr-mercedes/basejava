package com.urise.webapp.storage;

public abstract class AbstractStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;

    protected int size = 0;

    public int size() {
        return size;
    }
}
