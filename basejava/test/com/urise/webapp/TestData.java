package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.UUID;

public class TestData {
    public static final String UUID_1 = String.valueOf(UUID.randomUUID());
    public static final String UUID_2 = String.valueOf(UUID.randomUUID());
    public static final String UUID_3 = String.valueOf(UUID.randomUUID());
    public static final String UUID_4 = String.valueOf(UUID.randomUUID());

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.setContact(ContactType.MAIL, "mail1@ya.ru");
        R1.setContact(ContactType.PHONE, "11111");
        R4.setContact(ContactType.PHONE, "44444");
        R4.setContact(ContactType.SKYPE, "Skype");


        R1.setSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.setSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.setSection(SectionType.ACHIEVEMENT, new ListSection("Achievement11", "Achievement12", "Achievement13"));
        R1.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "https://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
        R1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "https://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT faculty")),
                        new Organization("Organization12", "https://Organization12.ru")));
        R2.setContact(ContactType.SKYPE, "skype2");
        R2.setContact(ContactType.PHONE, "22222");

    }
}
