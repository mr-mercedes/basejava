package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    public void save(Resume r) {
        int index = getIndex(r.toString());
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Storage is full");
        } else if (index != -1) {
            System.out.println("Resume already exist");
        } else {
            storage[size++] = r;
        }
    }
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.printf("ERROR: Item %s not found", uuid);
        }
    }
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
