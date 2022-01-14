package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    //fullName
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        for (Resume r : map.values()) {
            if (uuid.equals(r.getUuid())) {
                return r.getFullName();
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove((String) searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        var result = map.values().stream().toList();
        Collections.sort(result);
        return result;
    }

    @Override
    public int size() {
        return map.size();
    }
}
