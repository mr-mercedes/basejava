package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ResumeTestData {
    protected Resume createResume(String uuid, String fullName){
        return new Resume(uuid, fullName);
    }
}
