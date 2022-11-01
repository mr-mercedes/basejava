package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Storage is full");
        } else {
            storage[size++] = r;
        }
    }

    public void update(Resume r) {
        storage[getIndex(r.toString())].setUuid(r.toString());
    }

    public Resume get(String uuid) {
        int indexOf = getIndex(uuid);
        if (indexOf == -1) {
            System.out.printf("ERROR: Item %s not found\n", uuid);
            return null;
        }
        return storage[indexOf];
    }

    public void delete(String uuid) {
        int indexOf = getIndex(uuid);
        if (indexOf != -1) {
            System.arraycopy(storage, indexOf + 1, storage, indexOf, size--);
        } else {
            System.out.printf("ERROR: Item %s not found", uuid);
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
