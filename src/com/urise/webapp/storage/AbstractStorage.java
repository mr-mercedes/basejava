package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, SK file);

    protected abstract boolean isExist(SK file);

    protected abstract void doSave(Resume r, SK file);

    protected abstract Resume doGet(SK file);

    protected abstract void doDelete(SK file);

    protected abstract List<Resume> doCopyAll();

    public void update(Resume r) {
        LOG.info("Update" + r);
        SK file = getExistedfile(r.getUuid());
        doUpdate(r, file);
    }

    public void save(Resume r) {
        LOG.info("Save" + r);
        SK file = getNotExistedfile(r.getUuid());
        doSave(r, file);
    }

    public void delete(String uuid) {
        LOG.info("Delete" + uuid);
        SK file = getExistedfile(uuid);
        doDelete(file);
    }

    public Resume get(String uuid) {
        LOG.info("Get" + uuid);
        SK file = getExistedfile(uuid);
        return doGet(file);
    }

    private SK getExistedfile(String uuid) {
        SK file = getSearchKey(uuid);
        if (!isExist(file)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return file;
    }

    private SK getNotExistedfile(String uuid) {
        SK file = getSearchKey(uuid);
        if (isExist(file)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return file;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
