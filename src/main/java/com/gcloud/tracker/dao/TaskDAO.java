package com.gcloud.tracker.dao;

import com.gcloud.tracker.model.Task;
import com.gcloud.tracker.util.ConnectionMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class Task
 *
 * @author Igor Rogachev
 * @version 0.0.2
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

    private static final String SQL_FIND_TASK_BY_ID = "SELECT * FROM time_tracker.tasks WHERE id=?";
    private static final String SQL_FIND_ALL_TASK = "SELECT * FROM time_tracker.tasks";
    private static final String SQL_FIND_TASK_BY_USER_ID_AND_DATE = "SELECT * FROM time_tracker.tasks WHERE user_id = ? AND date = ?";
    private static final String SQL_CREATE_TASK = "INSERT INTO time_tracker.tasks(user_id, date, description, hours, minutes) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_EDIT_TASK = "UPDATE time_tracker.tasks SET user_id=?, date=?, description=?, hours=?, minutes=? WHERE id=?";
    private static final String SQL_DELETE_TASK = "DELETE FROM time_tracker.tasks WHERE id=?";

    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        try (
             Connection conn = ConnectionMaker.getInstance().getConnection();
             Statement statement = conn.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_TASK);
            getListTasks(tasks, resultSet);
        } catch (SQLException se) {
            log.error("Can't connect to database", se);
        } catch (ClassNotFoundException e) {
            log.error("Can't find database driver", e);
        }
        return tasks;
    }

    public Optional<Task> getById(Integer id) {
        Task task = null;
        try (
                Connection conn = ConnectionMaker.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_TASK_BY_ID);
        ) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Integer user_id = resultSet.getInt("user_id");
                LocalDate date = Date.valueOf(resultSet.getString("date")).toLocalDate();
                String description = resultSet.getString("description");
                Integer hour = resultSet.getInt("hours");
                Integer minutes = resultSet.getInt("minutes");
                task = new Task(id, user_id, date, description, hour, minutes);
            }
        } catch (SQLException se) {
            log.error("Can't connect to database", se);
        } catch (ClassNotFoundException e) {
            log.error("Can't find database driver", e);
        }
        return Optional.of(task);
    }

    public List<Task> findTaskByUserIdAndDate(Integer userId, LocalDate date){
        List<Task> tasks = new ArrayList<>();
        try (
                Connection conn = ConnectionMaker.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_TASK_BY_USER_ID_AND_DATE);
        ) {
            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(date));
            ResultSet resultSet = ps.executeQuery();
            getListTasks(tasks, resultSet);
        } catch (SQLException se) {
            log.error("Can't connect to database", se);
        } catch (ClassNotFoundException e) {
            log.error("Can't find database driver", e);
        }
        return tasks;
    }

    public void create(Task task) {
        try (
                Connection conn = ConnectionMaker.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_CREATE_TASK)
        ) {
            ps.setInt(1, task.getUserId());
            ps.setDate(2, Date.valueOf(task.getDate()));
            ps.setString(3, task.getDescription());
            ps.setInt(4, task.getHours());
            ps.setInt(5, task.getMinutes());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException se) {
            log.error("Can't connect to database", se);
        } catch (ClassNotFoundException e) {
            log.error("Can't find database driver", e);
        }
    }

    public void edit(Task task) {
        try (
                Connection conn = ConnectionMaker.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_EDIT_TASK)
        ) {
            ps.setInt(1, task.getUserId());
            ps.setDate(2, Date.valueOf(task.getDate()));
            ps.setString(3, task.getDescription());
            ps.setInt(4, task.getHours());
            ps.setInt(5, task.getMinutes());
            ps.setInt(6, task.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException se) {
            log.error("Can't connect to database", se);
        } catch (ClassNotFoundException e) {
            log.error("Can't find database driver", e);
        }
    }

    public void delete(Integer id) {
        try (
                Connection conn = ConnectionMaker.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_DELETE_TASK)
        ) {
            ps.setInt(1, id);
            conn.commit();
        } catch (SQLException se) {
            log.error("Can't connect to database", se);
        } catch (ClassNotFoundException e) {
            log.error("Can't find database driver", e);
        }
    }

    private void getListTasks(List<Task> tasks, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getInt("id"));
            task.setUserId(resultSet.getInt("user_id"));
            task.setDate(resultSet.getDate("date").toLocalDate());
            task.setDescription(resultSet.getString("description"));
            task.setHours(resultSet.getInt("hours"));
            task.setMinutes(resultSet.getInt("minutes"));
            tasks.add(task);
        }
    }
}