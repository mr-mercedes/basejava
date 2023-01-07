package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public abstract class AbstractStorageTest {
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }
    private static final Resume RESUME_SAVE = new ResumeTestData().createResume("saveCompleted", "Test Name");
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
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_1));
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
    public void getAllSorted(){
        List<Resume> testList = storage.getAllSorted();
        List<Resume> tmpList = Arrays.asList(R1, R2, R3);
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
        storage.save(R1);
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