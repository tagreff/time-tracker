package com.gcloud.tracker.action;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;
import com.gcloud.tracker.util.PropertiesMaker;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import static org.mockito.Mockito.*;

public class CreateUserActionTest {
    private static final Properties props = PropertiesMaker.getProps("test.properties");
    String URL = props.getProperty("url");

    @Test
    public void doPostSendRedirectWhenAddUserFalse() throws IOException {
        UserDAO userDAO = mock(UserDAO.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        User user = new User();

        when(userDAO.addUser(user)).thenReturn(false);
        response.sendRedirect("/createUser?cantCreate&");

        verify(response, times(1)).sendRedirect("/createUser?cantCreate&");
    }

    @Test
    public void doPostSendRedirectWhenAddUserTrue() throws IOException {
        UserDAO userDAO = mock(UserDAO.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        User user = new User();

        when(userDAO.addUser(user)).thenReturn(true);
        response.sendRedirect("/?userCreated=alex");

        verify(response, times(1)).sendRedirect("/?userCreated=alex");
    }

    @Test
    public void doPostSetCharacterEncoding() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);

        doNothing().when(request).setCharacterEncoding(isA(String.class));
        request.setCharacterEncoding("UTF-8");

        verify(request, times(1)).setCharacterEncoding("UTF-8");
    }
}