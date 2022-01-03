package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int id = getIndex(r.getUuid());
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
        if (getIndex(r.getUuid()) == -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
            return;
        }
        storage[size++] = r;
    }

    public void delete(String uuid) {
        int id = getIndex(uuid);
        if (id == -1) {
            System.out.println("Resume " + uuid + " already exist");
            return;
        }
        System.arraycopy(storage, id + 1, storage, id, size - id - 1);
        storage[--size] = null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equalsIgnoreCase(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
