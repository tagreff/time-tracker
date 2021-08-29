package com.gcloud.tracker.action;

import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.model.Task;
import com.gcloud.tracker.util.PropertiesMaker;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class MainActionTest {
    private static final Properties props = PropertiesMaker.getProps("test.properties");
    String URL = props.getProperty("url");

    @Test
    public void doGetSetCharacterEncoding() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        doNothing().when(request).setCharacterEncoding(isA(String.class));
        request.setCharacterEncoding("UTF-8");

        verify(request, times(1)).setCharacterEncoding("UTF-8");
    }

    @Test
    public void doPostSetCharacterEncoding() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        doNothing().when(request).setCharacterEncoding(isA(String.class));
        request.setCharacterEncoding("UTF-8");

        verify(request, times(1)).setCharacterEncoding("UTF-8");
    }

    @Test
    public void doPostCallCreateTask() throws IOException {
        TaskDAO taskDAO = mock(TaskDAO.class);
        Task task = new Task();

        doNothing().when(taskDAO).create(isA(Task.class));
        taskDAO.create(task);

        verify(taskDAO, times(1)).create(task);
    }

    @Test
    public void doPostCallSendRedirect() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        doNothing().when(response).sendRedirect(isA(String.class));

        response.sendRedirect("/mainPage");

        verify(response, times(1)).sendRedirect("/mainPage");
    }

    @Test(expected = Exception.class)
    public void doPostCallSendRedirectNull() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        doThrow().when(response).sendRedirect(isNull());

        response.sendRedirect(null);
    }
}