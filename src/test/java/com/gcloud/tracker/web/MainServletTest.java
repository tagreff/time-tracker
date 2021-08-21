package com.gcloud.tracker.web;

import com.gcloud.tracker.web.LoginServlet;
import com.gcloud.tracker.web.MainServlet;
import org.junit.Ignore;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class MainServletTest {

    private final static String path = "/WEB-INF/mainPage.jsp";

    @Ignore
    @Test
    public void whenCallDoGetMainServletReturnIndexPageWithTaskList() throws ServletException, IOException {

        final MainServlet servlet = new MainServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(path);   // analogue assert in JUnit
        verify(dispatcher).forward(request, response);
    }
}
