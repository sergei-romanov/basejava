package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapStorage extends AbstractStorage {


    @Override
    protected Object getFindKey(String uuid) {
        return null;
    }

    @Override
    protected boolean isExist(Object key) {
        return false;
    }

    @Override
    protected void doUpdate(Object key, Resume r) {

    }

    @Override
    protected void doDelete(Object key) {

    }

    @Override
    protected void doSave(Resume r) {

    }

    @Override
    protected Resume doGet(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
