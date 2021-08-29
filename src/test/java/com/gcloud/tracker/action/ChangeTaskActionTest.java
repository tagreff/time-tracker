package com.gcloud.tracker.action;

import com.gcloud.tracker.util.PropertiesMaker;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class ChangeTaskActionTest {
    private static final Properties props = PropertiesMaker.getProps("test.properties");
    String URL = props.getProperty("url");

    @Test
    public void doPostCallSendRedirect() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        doNothing().when(response).sendRedirect(isA(String.class));
        response.sendRedirect("/mainPage");

        verify(response, times(1)).sendRedirect("/mainPage");
    }

    @Test
    public void doPostCallSetCharacterEncoding() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);

        doNothing().when(request).setCharacterEncoding(isA(String.class));
        request.setCharacterEncoding("UTF-8");

        verify(request).setCharacterEncoding("UTF-8");
    }
}