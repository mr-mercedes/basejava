package com.urise.webapp.storage;

public class MapUuidStorage extends MapStorage {


    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

}
