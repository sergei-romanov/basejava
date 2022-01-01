package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int id = getId(r.getUuid());
        if (id == -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
            return;
        }
        storage[id] = r;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage overflow");
            return;
        }
        if (getId(r.getUuid()) == -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
            return;
        }
        storage[size++] = r;
    }

    public Resume get(String uuid) {
        int id = getId(uuid);
        if (id == -1) {
            System.out.println("Resume " + uuid + " already exist");
            return null;
        }
        return storage[id];
    }

    public void delete(String uuid) {
        int id = getId(uuid);
        if (id == -1) {
            System.out.println("Resume " + uuid + " already exist");
            return;
        }
        System.arraycopy(storage, id + 1, storage, id, size - id - 1);
        storage[--size] = null;
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getId(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equalsIgnoreCase(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
