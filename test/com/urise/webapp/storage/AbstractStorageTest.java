package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public abstract class AbstractStorageTest {
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Sergey Borovikov");
    private static final Resume RESUME_2 = new Resume(UUID_2, "Maria Borovikova");
    private static final Resume RESUME_3 = new Resume(UUID_3, "Vladimir Ivanov");
    private static final Resume RESUME_SAVE = new Resume("saveCompleted", "Test Name");
    private static int expectedSize = 0;

    public AbstractStorageTest(Storage storage) {
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

    @Test
    public void size() {
        assertSize(expectedSize);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }


    @Test
    public void clear() {
        expectedSize = 0;
        storage.clear();
        assertSize(expectedSize);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "Update Name");
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_1));
    }

    @Test
    public void save() {
        expectedSize += 1;
        storage.save(RESUME_SAVE);
        assertGet(RESUME_SAVE);
        assertSize(expectedSize);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        expectedSize -= 1;
        String[] existUuid = {UUID_1, UUID_2, UUID_3};
        for (int i = 0; i < existUuid.length; i++) {
            storage.delete(existUuid[i]);
            assertSize(expectedSize - i);
        }
        storage.get(UUID_2);
    }

    @Test
    public void getAllSorted(){
        List<Resume> testList = storage.getAllSorted();
        List<Resume> tmpList = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(tmpList);
        expectedSize = testList.size();
        Assert.assertEquals(expectedSize, testList.size());
        Assert.assertEquals(testList, tmpList);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_SAVE);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_SAVE.getUuid());
    }

    private void assertGet(Resume r) {
        Resume compareResume = storage.get(r.getUuid());
        Assert.assertEquals(r, compareResume);
    }

    private void assertSize(int param) {
        Assert.assertEquals(param, storage.size());
    }
}