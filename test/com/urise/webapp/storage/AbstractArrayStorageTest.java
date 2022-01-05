package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.stream.IntStream;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        var r1 = new Resume(UUID_1);
        var r2 = new Resume(UUID_2);
        var r3 = new Resume(UUID_3);
        Resume[] expect = {r1, r2, r3};
        Assertions.assertArrayEquals(expect, storage.getAll());
    }

    @Test
    public void save() throws Exception {
        var r1 = new Resume(UUID_1);
        var r2 = new Resume(UUID_2);
        var r3 = new Resume(UUID_3);
        var r4 = new Resume("uuid4");
        Resume[] expect = {r1, r2, r3, r4};
        storage.save(r4);
        Assertions.assertArrayEquals(expect, storage.getAll());
    }

    @Test
    public void delete() throws Exception {
        var r1 = new Resume(UUID_1);
        var r2 = new Resume(UUID_2);
        Resume[] expect = {r1, r2};
        storage.delete(UUID_3);
        Assertions.assertArrayEquals(expect, storage.getAll());
    }

    @Test
    public void get() throws Exception {
        var expect = new Resume(UUID_1);
        var actual = storage.get(UUID_1);
        Assertions.assertEquals(expect, actual);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void storageFull() throws Exception {
        try {
            IntStream.range(3, AbstractArrayStorage.STORAGE_LIMIT).mapToObj(i -> new Resume()).forEach(storage::save);
        } catch (StorageException e) {
            Assertions.fail();
        }
        storage.save(new Resume());
    }
}