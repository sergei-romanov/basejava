package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void transactionExecute(String request) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(request)) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
    public <T> T transactionExecute(String request, ABlockOfCode<T> aBlockOfCode) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(request)) {
            ps.execute();
            return aBlockOfCode.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
