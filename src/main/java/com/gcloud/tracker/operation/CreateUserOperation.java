package com.gcloud.tracker.operation;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserOperation implements Operation{
    UserDAO userDAO = new UserDAO();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getServletContext().getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
