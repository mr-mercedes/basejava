package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    protected static final Collection<Resume> STORAGE = new ArrayList<>();

    @Override
    public int size() {
        return STORAGE.size();
    }

    @Override
    public Resume get(String uuid) {
        for (Resume resume : STORAGE) {
            if (Objects.equals(resume.toString(), uuid)) {
                return resume;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public void update(Resume r) {
        Iterator<Resume> iterator = STORAGE.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (resume == r) {
                iterator.remove();
                STORAGE.add(r);
            }
        }
    }

    @Override
    public void save(Resume r) {
        if (STORAGE.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        }
        STORAGE.add(r);
    }

    @Override
    public void delete(String uuid) {
        Resume resume = get(uuid);
        STORAGE.remove(resume);
    }

    @Override
    public Resume[] getAll() {
        return STORAGE.toArray(new Resume[0]);
    }
}
