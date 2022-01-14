package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    //fullName
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        for (String s : map.keySet()) {
            if (uuid.equals(map.get(s).getUuid())) {
                return map.get(s).getFullName();
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put(r.getFullName(), r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getFullName(), r);
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
        var resul = new ArrayList<Resume>();
        for (String s : map.keySet()) {
            resul.add(map.get(s));
        }
        Collections.sort(resul);
        return resul;
    }

    @Override
    public int size() {
        return map.keySet().size();
    }

    public static void main(String[] args) {
        var RESUME_1 = new Resume("UUID_1");
        RESUME_1.setFullName("Ar Ser");
        var RESUME_2 = new Resume("UUID_2");
        RESUME_2.setFullName("Bur Gamer");
        var RESUME_3 = new Resume("UUID_3");
        RESUME_3.setFullName("Salt Frank");
        MapUuidStorage mp = new MapUuidStorage();
        mp.save(RESUME_1);
        mp.save(RESUME_2);
        mp.save(RESUME_3);
        System.out.println(mp.getAllSorted());
    }
}
