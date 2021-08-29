package com.gcloud.tracker.action;

import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.util.PropertiesMaker;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import static org.mockito.Mockito.*;

public class DeleteTaskActionTest {
    private static final Properties props = PropertiesMaker.getProps("test.properties");
    String URL = props.getProperty("url");

    @Test
    public void whenCallDoPostThenServletCallMethodDelete() throws IOException {

        final TaskDAO taskDAO = mock(TaskDAO.class);

        doNothing().when(taskDAO).delete(isA(Integer.class));
        taskDAO.delete(1);

        verify(taskDAO, times(1)).delete(1);
    }

    @Test(expected = Exception.class)
    public void whenCallDoPostThenServletThrowException() throws IOException {

        final TaskDAO taskDAO1 = mock(TaskDAO.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        doThrow().when(taskDAO1).delete(isNull());
        taskDAO1.delete(null);
    }

    @Test
    public void whenCallDoPostThenServletCallMethodSendRedirect() throws IOException {
        final HttpServletResponse response = mock(HttpServletResponse.class);

        doNothing().when(response).sendRedirect(isA(String.class));
        response.sendRedirect("/mainPage");

        verify(response).sendRedirect("/mainPage");
    }
}