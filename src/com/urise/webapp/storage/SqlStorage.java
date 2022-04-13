package com.urise.webapp.storage;


import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.transactionExecute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionExecute("SELECT * FROM resume r WHERE r.uuid =?", e -> {
            e.setString(1, uuid);
            ResultSet rs = e.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.<Void>transactionExecute("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.<Void>transactionExecute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", e -> {
            e.setString(1, r.getUuid());
            e.setString(2, r.getFullName());
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>transactionExecute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            ps.execute();
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionExecute("SELECT * FROM resume ORDER BY full_name, uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return result;
        });
    }

    @Override
    public int size() {
        return sqlHelper.transactionExecute("SELECT count(*) resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}

