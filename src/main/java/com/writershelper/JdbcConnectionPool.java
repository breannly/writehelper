package com.writershelper;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class JdbcConnectionPool {
    private static final String URL = "jdbc:mysql://localhost:3306/writerhelper";
    private static final String USER = "";
    private static final String PASSWORD = "";

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

