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
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Inject
    UserVerificationBean userVerificationBean;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

        if(info.equals(userVerificationBean.SUCCESS)) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }
}