package com.urise.webapp.storage;


import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.urise.webapp.TestData.*;


public abstract class AbstractStorageTest {
    protected final Storage storage;
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    private static int expectedSize = 0;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
        expectedSize = 3;
    }

    @Test
    public void size() {
        assertSize(expectedSize);
    }

    @Test
    public void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }


    @Test
    public void clear() {
        expectedSize = 0;
        storage.clear();
        assertSize(expectedSize);
    }

    @Test
    public void update() {
        Resume newResume = new ResumeTestData().createResume(UUID_1, "Update Name");
        R1.setContact(ContactType.MAIL, "mail@google.com");
        R1.setContact(ContactType.SKYPE, "newSkype");
        R1.setContact(ContactType.MOBILE, "+7-967-139-96-01");
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    public void save() {
        expectedSize += 1;
        storage.save(R4);
        assertGet(R4);
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
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        List<Resume> sortedResumes = Arrays.asList(R1, R2, R3);
        Collections.sort(sortedResumes);
        Assert.assertEquals(sortedResumes, list);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(R4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(R4.getUuid());
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int param) {
        Assert.assertEquals(param, storage.size());
    }
}