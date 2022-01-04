package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume r) {
        int index = findIndex(r);
        System.arraycopy(storage, index + 1, storage, index + 2, size - index - 1);
        storage[index + 1] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    private int findIndex(Resume r) {
        var mid = 0;
        var start = 0;
        var end = size;
        while (start <= end) {
            mid = (start + end) / 2;
            var prev = storage[mid - 1].getUuid();
            var next = storage[mid].getUuid();
            if (r.getUuid().compareTo(prev) >= 0 && r.getUuid().compareTo(next) <= 0) {
                return mid;
            }
            if (r.getUuid().compareTo(next) > 0) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return mid;
    }
}