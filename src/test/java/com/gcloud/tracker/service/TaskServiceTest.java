package com.gcloud.tracker.service;

import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskDAO taskDAO;

    @InjectMocks
    private TaskService taskService;

    private List<Task> tasksTest = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        tasksTest.clear();

        Task task1 = new Task();
        task1.setId(1);
        task1.setUserId(1);
        task1.setDate(LocalDate.now());
        task1.setDescription("Some task1");
        task1.setHours(1);
        task1.setMinutes(30);

        Task task2 = new Task();
        task2.setId(2);
        task2.setUserId(1);
        task2.setDate(LocalDate.now());
        task2.setDescription("Some task2");
        task2.setHours(2);
        task2.setMinutes(0);
    }

    @Test
    public void getDailyUserTaskTest() {
        when(taskDAO.findTaskByUserIdAndDate(1, LocalDate.now())).thenReturn(tasksTest);

        List<Task> tasksExist = taskService.getDailyUserTask(1, LocalDate.now());

        assertThat(tasksExist).isEqualTo(tasksTest);
    }
}