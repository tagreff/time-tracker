package com.gcloud.tracker.dao;

import com.gcloud.tracker.model.User;
import com.gcloud.tracker.util.ConnectionMaker;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class UserDAO
 *
 * Data Access Object.
 *
 * @author Oleksandr Storozhuk
 * @version 0.0.3
 * created on 18.08.2021
 */
public class UserDAO {
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    private final String SQL_FIND_BY_LOGIN = "SELECT * FROM time_tracker.users WHERE login=?";
    private final String SQL_FIND_ALL_USER = "SELECT * FROM time_tracker.users";
    private final String SQL_ADD_USER = "INSERT INTO time_tracker.users" +
            "(login, first_name, last_name, password, role_id)  VALUES (?, ?, ?, ?, ?)";


    /**
     * Find user by login
     * @param login - unique user login string
     * @return <code>User</code> wrapped into <code>Optional</code> if exists, else Optional.empty().
     */
    @SneakyThrows
    public Optional<User> findByLogin(String login){
        try (Connection conn = ConnectionMaker.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_BY_LOGIN)) {
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    return Optional.of(new User()
                            .setId(resultSet.getInt("id"))
                            .setLogin(resultSet.getString("login"))
                            .setFirstName(resultSet.getString("first_name"))
                            .setLastName(resultSet.getString("last_name"))
                            .setPassword(resultSet.getString("password"))
                            .setRoleID(resultSet.getInt("role_id")));
                }
            }

        return Optional.empty();
    }

    /**
     * Find all users
     * @return <code>List<User></code>
     */
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (
                Connection conn = ConnectionMaker.getInstance().getConnection();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USER)
        ) {
            while (resultSet.next()) {
                User user = new User()
                        .setId(resultSet.getInt("id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password"))
                        .setRoleID(resultSet.getInt("role_id"));

                users.add(user);
            }
        } catch (SQLException se) {
            log.error("Can't connect to database", se);
        } catch (ClassNotFoundException e) {
            log.error("Can't find database driver", e);
        }
        return users;
    }

    /**
     * Add User to data source.
     * @param user - user to add.
     * @return true if success, else false.
     */
    public boolean addUser(User user) {
        if(user == null )
            return false;
        try (Connection conn = ConnectionMaker.getInstance().getConnection()) {
            try(PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_USER)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getFirstName());
                preparedStatement.setString(3, user.getLastName());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setInt(5, user.getRoleID());

                return preparedStatement.executeUpdate() == 1;
            }
        } catch (SQLException se) {
            log.error("Can't connect to database!", se);
        } catch (ClassNotFoundException e) {
            log.error("Can't find database driver!", e);
        }
        return false;
    }
}