package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.toString());
        if (index < 0) {
            System.out.println("ERROR: Resume not exist");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.toString());
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Storage is full");
        } else if (index >= 0) {
            System.out.println("Resume already exist");
        } else {
            saveResume(r);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            deleteResume(index);
        } else {
            System.out.printf("ERROR: Item %s not found", uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(int index);
}
