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

//设置访问路径，请求
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject
    UserVerificationBean userVerificationBean;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user=userVerificationBean.verifyIdentidy(username, password);
        if ((user==null)) {
            request.setAttribute("info", "Sorry! Password not inValid!");
            request.getRequestDispatcher("message.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("username", username);
        session.setAttribute("info", "Login Successful!");
        session.setAttribute("isFavoritePage", false);
        //获取用户的收藏列表
        // 跳转/que ,使用get请求
        response.sendRedirect("viewpoints");
    }

}
