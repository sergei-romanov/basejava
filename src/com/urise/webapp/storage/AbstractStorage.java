package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getFindKey(String uuid);

    protected abstract boolean isExist(Object key);

    //protected abstract boolean checkContainsKey(Object k);

    protected abstract void doUpdate(Object key, Resume r);

    protected abstract void doDelete(Object key);

    protected abstract void doSave(Resume r);

    protected abstract Resume doGet(Object key);

    public void update(Resume r) {
        Object findKey = getFindKey(r.getUuid());
        if (!isExist(findKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        doUpdate(findKey, r);
    }

    public void save(Resume r) {
        Object findKey = getFindKey(r.getUuid());
        if (isExist(findKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        doSave(r);
    }

    public Resume get(String uuid) {
        Object findKey = getFindKey(uuid);
        if (!isExist(findKey)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(findKey);
    }

    public void delete(String uuid) {
        Object findKey = getFindKey(uuid);
        if (!isExist(findKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(findKey);
        }
    }
}
