package com.example.bhw.Servlet;
import com.example.bhw.Dao.UserDao;

import com.example.bhw.Bean.QueueStatusBean;
import com.example.bhw.Bean.UserManageBean;
import com.example.bhw.Bean.EmailServiceBean;


import com.example.bhw.Entity.User;
import com.example.bhw.Entity.Viewpoint;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Inject
    UserManageBean userManageBean;

    @Inject
    EmailServiceBean emailServiceBean;

    @Inject
    private UserDao userDao;

    @Inject
    private QueueStatusBean queueStatusBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            handleLogin(request, response);
        } else {
            request.setAttribute("info", "Invalid action.");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            handleRegister(request, response);
        } else if ("sendCode".equals(action)) {
            handleSendCode(request, response);
        } else if ("changePassword".equals(action)) {
            handleChangePassword(request, response);
        } else {
            request.setAttribute("info", "Invalid action.");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        String info = userManageBean.RegisterUser(user, repassword);
        HttpSession session = request.getSession();
        session.setAttribute("info", info);

        if (info.equals(UserManageBean.SUCCESS)) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userManageBean.verifyIdentidy(username, password);
        if (user == null) {
            request.setAttribute("info", "Sorry! Password not valid!");
            request.getRequestDispatcher("message.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("username", username);
        session.setAttribute("info", "Login Successful!");
        session.setAttribute("isFavoritePage", false);
        response.sendRedirect("viewpoints?action=showHome");
    }

    private void handleSendCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        User user = userDao.getUserByName(username);
        if (user != null) {
            String email = user.getEmail();
            String code = emailServiceBean.generateVerificationCode();
            emailServiceBean.sendEmail(email, "Verification Code", "Your verification code is: " + code);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("verificationCode", code);
            session.setAttribute("email", email);
            session.setAttribute("info", "Verification code sent to your email.");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("info", "User not found.");
            response.sendRedirect("changePassword.jsp");
        }
    }

    private void handleChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String newPassword = request.getParameter("newPassword");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String sessionCode = (String) session.getAttribute("verificationCode");
        String sessionEmail = (String) session.getAttribute("email");

        if (sessionCode != null && sessionCode.equals(code) && sessionEmail != null && sessionEmail.equals(email)) {
            boolean isChanged = userManageBean.changePassword(username, newPassword);
            if (isChanged) {
                session.setAttribute("info", "Password changed successfully.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                session.setAttribute("info", "Failed to change password.");
                request.getRequestDispatcher("message.jsp").forward(request, response);
            }
        } else {
            session.setAttribute("info", "Invalid verification code or email.");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

}
