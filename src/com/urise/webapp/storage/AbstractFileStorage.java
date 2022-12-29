package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error ", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(File file) {
        File[] files = this.directory.listFiles();
        if (files != null){
            for (File item : files) {
                if (item.getName().equalsIgnoreCase(file.getName())){
                    item.delete();
                }
            }
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = this.directory.listFiles();
        List<Resume> resumes = new ArrayList<>();
        assert files != null;
        for (File file : files) {
            try {
                resumes.add(doRead(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] files = this.directory.listFiles();
        if (files != null){
            for (File file : files) {
                file.delete();
            }
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(this.directory.listFiles()).length;
    }
    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}
