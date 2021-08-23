package com.gcloud.tracker.web;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 21/08/21
 */
@WebServlet("/createUser")
public class CreateServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        User user = new User()
                .setLogin(req.getParameter("login"))
                .setFirstName(req.getParameter("firstName"))
                .setLastName(req.getParameter("lastName"))
                .setPassword(DigestUtils.sha1Hex(req.getParameter("password")))
                .setRoleID(1);
        if (userDAO.addUser(user)) {
            resp.sendRedirect("/?userCreated=" + user.getLogin());
        } else {
            resp.sendRedirect("/createUser?cantCreate&");
        }
    }
}
