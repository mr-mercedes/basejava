package com.urise.webapp.storage;


import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;


public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp(){
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }
    @org.junit.Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @org.junit.Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
        Assert.assertEquals(new Resume(UUID_2), storage.get(UUID_2));
        Assert.assertEquals(new Resume(UUID_3), storage.get(UUID_3));
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @org.junit.Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @org.junit.Test (expected = NotExistStorageException.class)
    public void update() {
        storage.update(new Resume("uuid4"));
    }

    @org.junit.Test
    public void save() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException ex){
            if (storage.size() >= AbstractArrayStorage.STORAGE_LIMIT){
                Assert.assertTrue("Storage overflow on add \"uuid10001\" Resume",true);
            } else {
                Assert.fail("Storage overflow early");
            }
        }
    }

    @org.junit.Test
    public void delete() {
        storage.delete(UUID_1);
    }

    @org.junit.Test
    public void getAll() {
        Resume[] testStorage = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(testStorage,storage.getAll());
    }
}