package com.urise.webapp.storage;


import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class AbstractMapStorageTest extends AbstractStorageTest {
    public AbstractMapStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void getAllSorted() {
        List<Resume> testList = storage.getAllSorted();
        List<Resume> tmpList = new ArrayList<>();
        Collections.sort(tmpList);
        Assert.assertEquals(testList.size(), testList.size());
        Assert.assertEquals(testList, tmpList);
    }
}