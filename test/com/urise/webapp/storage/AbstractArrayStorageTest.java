package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
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
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_SAVE = new Resume("saveCompleted");
    private static int expectedSize = 0;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        expectedSize = 3;
    }

    @org.junit.Test
    public void size() {
        assertSize(expectedSize);
    }

    @org.junit.Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @org.junit.Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_SAVE);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_SAVE.getUuid());
    }


    @org.junit.Test
    public void clear() {
        expectedSize = 0;
        Resume[] compareResume = new Resume[expectedSize];
        storage.clear();
        assertSize(expectedSize);
        Assert.assertEquals(expectedSize, storage.size());
        Assert.assertArrayEquals(storage.getAll(), compareResume);
    }

    @org.junit.Test
    public void update() {
        Assert.assertSame(RESUME_1, storage.get(UUID_1));
    }

    @org.junit.Test
    public void save() {
        expectedSize += 1;
        storage.save(RESUME_SAVE);
        assertGet(RESUME_SAVE);
        assertSize(expectedSize);
    }

    @org.junit.Test(expected = StorageException.class)
    public void saveOverflow() {
        final Resume RESUME_OVERFLOW = new Resume("uuidOverflow");
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException ex) {
            Assert.fail();
        }
        storage.save(RESUME_OVERFLOW);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void delete() {
        expectedSize -= 1;
        String[] existUuid = {UUID_1, UUID_2, UUID_3};
        for (int i = 0; i < existUuid.length; i++) {
            storage.delete(existUuid[i]);
            assertSize(expectedSize - i);
        }
        storage.get(UUID_2);
    }

    @org.junit.Test
    public void getAll() {
        Resume[] testStorage = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(testStorage, storage.getAll());
        assertSize(expectedSize);
    }

    private void assertSize(int param) {
        Assert.assertEquals(param, storage.size());
    }

    private void assertGet(Resume r) {
        Resume compareResume = storage.get(r.getUuid());
        Assert.assertEquals(r, compareResume);
    }
}