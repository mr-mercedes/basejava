package com.urise.webapp.storage;

public class MapFullNameStorage extends MapStorage {


    @Override
    protected Object getSearchKey(String fullName) {
        return fullName;
    }

}
