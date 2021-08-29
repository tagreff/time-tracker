package com.gcloud.tracker.action;

import com.gcloud.tracker.util.PropertiesMaker;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import static org.mockito.Mockito.*;

public class LoginActionTest {

    private final static String path = "/WEB-INF/index.jsp";
    private static final Properties props = PropertiesMaker.getProps("test.properties");
    String URL = props.getProperty("url");

    @Test
    public void whenCallDoGetThenServletCallGetRequestDispatcher() throws ServletException, IOException {

        final LoginAction loginAction = new LoginAction();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        // задание условия: когда у request будет вызван метод getRequestDispatcher с параметром path, должен вернуться dispatcher
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        loginAction.doGet(request, response);

        // проверка того, что у объекта request в ншем методе doGet вызывается один раз (times(1)) метод getRequestDispatcher(path):
        verify(request, times(1)).getRequestDispatcher(path);   // analogue assert in JUnit
    }

    @Test
    public void whenCallDoGetThenServletCallForward() throws ServletException, IOException {

        final LoginAction loginAction = new LoginAction();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        loginAction.doGet(request, response);

        // проверка того, что у объекта dispatcher вызывается метод forward с параметрами request и response:
        verify(dispatcher).forward(request, response);  // analogue assert in JUnit
    }

    @Test
    public void whenCallDoGetThenServletNeverCallGetSession() throws ServletException, IOException {

        final LoginAction loginAction = new LoginAction();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        loginAction.doGet(request, response);

        // проверка того, что у объекта request никогда (never()) не вызывается метод getSession():
        verify(request, never()).getSession();
    }

    @Test
    public void onLoginFail() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        doNothing().when(response).sendRedirect(isA(String.class));
        response.sendRedirect("/?error&");

        verify(response, times(1)).sendRedirect("/?error&");
    }

    @Test
    public void onLoginSuccess() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        doNothing().when(response).sendRedirect(isA(String.class));
        response.sendRedirect("/mainPage");

        verify(response, times(1)).sendRedirect("/mainPage");
    }
}