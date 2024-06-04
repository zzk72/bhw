package com.example.bhw.Servlet;

import com.example.bhw.Bean.UserVerificationBean;
import com.example.bhw.Entity.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Inject
    UserVerificationBean userVerificationBean;

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
        String info = userVerificationBean.RegisterUser(user, repassword);
        HttpSession session = request.getSession();
        session.setAttribute("info", info);

        if (info.equals(UserVerificationBean.SUCCESS)) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userVerificationBean.verifyIdentidy(username, password);
        if (user == null) {
            request.setAttribute("info", "Sorry! Password not inValid!");
            request.getRequestDispatcher("message.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("username", username);
        session.setAttribute("info", "Login Successful!");
        session.setAttribute("isFavoritePage", false);
        response.sendRedirect("viewpoints");
    }
}
