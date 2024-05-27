package com.example.bhw.Servlet;

import com.example.bhw.Dao.UserDao;
import com.example.bhw.Dao.ViewpointDao;
import com.example.bhw.Entity.Orders;
import com.example.bhw.Entity.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/test")
public class testServlet extends HttpServlet {

    @Inject
    private UserDao userDao;
    @Inject
    private ViewpointDao viewpointDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<String> output=new ArrayList<>();
//        User user = userDao.getUser("zzk");
//        List<Orders> orders = userDao.getOrders(user.getId());
//        for (int i = 0; i < orders.size(); i++) {
//            if(i>3) break;
//            output.add(orders.get(i).toString());
//        }
//
//        //直接输出html
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().println("<html>");
//        response.getWriter().println("<head>");
//        response.getWriter().println("<title>Test</title>");
//        response.getWriter().println("</head>");
//        response.getWriter().println("<body>");
//        response.getWriter().println("<h1>Test</h1>");
//        response.getWriter().println("<p>用户信息</p>");
//        response.getWriter().println("<p>"+user.toString()+"</p>");
//        response.getWriter().println("<p>订单信息</p>");
//        for (String s : output) {
//            response.getWriter().println("<p>"+s+"</p>");
//        }
    }
}
