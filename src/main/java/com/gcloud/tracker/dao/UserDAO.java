package com.gcloud.tracker.dao;

import com.gcloud.tracker.model.User;
import com.gcloud.tracker.util.ConnectionMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    private final String SQL_FIND_BY_LOGIN = "SELECT * FROM time_tracker.users WHERE login=?";
    private final String SQL_ADD_USER = "INSERT INTO time_tracker.users" +
            "(login, first_name, last_name, password, role_id)  VALUES (?, ?, ?, ?, ?)";


    public Optional<User> findByLogin(String login){
        try (Connection conn = ConnectionMaker.getInstance().getConnection()) {

            try(PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_BY_LOGIN)) {
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
        } catch (SQLException se) {
            log.error("Can't connect to database!", se);
        } catch (ClassNotFoundException e) {
            log.error("Can't find database driver!", e);
        }
        return Optional.empty();
    }

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