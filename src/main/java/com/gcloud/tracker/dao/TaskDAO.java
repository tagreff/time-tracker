package com.gcloud.tracker.dao;


import com.gcloud.tracker.model.Task;
import com.gcloud.tracker.model.User;
import com.gcloud.tracker.util.ConnectionMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class Task
 *
 * @author Igor Rogachev
 * @version 0.0.1
 * created on 18.08.2021
 */

public class TaskDAO {

    private static TaskDAO instance = new TaskDAO();

    public static TaskDAO getInstance() {
        if (instance == null) {
            instance = new TaskDAO();
        }
        return instance;
    }

    private final static Logger log = LoggerFactory.getLogger(TaskDAO.class);
    private static ConnectionMaker connection = null;

    static {
        try {
            connection = ConnectionMaker.getInstance();
        } catch (SQLException sql) {
            log.error("SQLException", sql);
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException!", e);
        }
    }

    private static final String SQL_FIND_TASK_BY_ID = "SELECT * FROM task WHERE id=?";
    private static final String SQL_FIND_ALL_TASK = "SELECT * FROM task";
    private static final String SQL_CREATE_TASK = "INSERT INTO users(user_id, date, description, hours, minutes) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_EDIT_TASK = "UPDATE task SET user_id=?, date=?, description=?, hours=?, minutes=? WHERE id=?";
    private static final String SQL_DELETE_TASK = "DELETE FROM task WHERE id=?";

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Optional getById(Integer id) {
        Task task = null;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL_FIND_TASK_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String user_id = resultSet.getString("user_id");
                String date = resultSet.getString("date");
                Integer hour = resultSet.getInt("hours");
                Integer minutes = resultSet.getInt("minutes");
                task = new Task(id, user_id, date, hour, minutes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Task> findTaskByUserIdAndDate(Integer id, LocalDate date){
        return new ArrayList<>();
    }

    @Override
    public Optional create(AbstractDAO entity) {
        return Optional.empty();
    }

    @Override
    public Optional edit(AbstractDAO entity) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
