import com.gcloud.tracker.web.LoginServlet;
import com.gcloud.tracker.web.LoginServlet;
import org.junit.Ignore;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServletTest {

    private final static String path = "/WEB-INF/index.jsp";

    @Test
    public void whenCallDoGetThenServletReturnIndexPage() throws ServletException, IOException {

        final LoginServlet servlet = new LoginServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(path);   // analogue assert in JUnit
        verify(dispatcher).forward(request, response);
    }



}
