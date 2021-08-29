package com.gcloud.tracker.action;

import com.gcloud.tracker.util.PropertiesMaker;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import static org.mockito.Mockito.*;

public class SendReportActionTest {
    private static final Properties props = PropertiesMaker.getProps("test.properties");
    String URL = props.getProperty("url");

    @Test
    public void doGetCallMethodSendRedirect() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        doNothing().when(response).sendRedirect(isA(String.class));
        response.sendRedirect("/?report&");

        verify(response, times(1)).sendRedirect("/?report&");
    }
}