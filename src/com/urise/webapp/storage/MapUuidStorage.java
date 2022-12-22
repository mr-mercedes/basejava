package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey((String) uuid);
    }

    @Override
    protected void doSave(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    protected void doDelete(Object uuid) {
        storage.remove((String) uuid);
    }
}
