package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        int index = getIndex(r.toString());
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Storage is full");
        } else if (index >= 0) {
            System.out.println("Resume already exist");
        } else {
            System.arraycopy(storage, -index - 1, storage, -index, size++);
            storage[-index - 1] = r;
        }
    }
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            System.arraycopy(storage, index + 1, storage, index, size--);
        } else {
            System.out.printf("ERROR: Item %s not found", uuid);
        }
    }
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
