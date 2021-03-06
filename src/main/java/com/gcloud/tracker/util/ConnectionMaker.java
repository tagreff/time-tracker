package com.gcloud.tracker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class ConnectionMaker
 *
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 16.08.2021
 */
public class ConnectionMaker {
    private static ConnectionMaker instance;
    private final Connection con;
    private static final Properties props = PropertiesMaker.getProps("db.properties");

    private ConnectionMaker() throws SQLException, ClassNotFoundException {
        Class.forName(props.getProperty("connection.driver"));
        this.con = DriverManager.getConnection(
                props.getProperty("connection.url"),
                props.getProperty("connection.username"),
                props.getProperty("connection.password"));
    }

    public Connection getConnection() {
        return con;
    }

    /**
     * Get instance of current class.
     *
     * @return instance of ConnectionMaker.
     * @throws SQLException if connection to database failed.
     * @throws ClassNotFoundException if driver not found.
     */
    public static ConnectionMaker getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConnectionMaker();
        }
        return instance;
    }
}
