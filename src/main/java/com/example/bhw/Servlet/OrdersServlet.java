package com.example.bhw.Servlet;

import com.example.bhw.Dao.OrdersDao;
import com.example.bhw.Entity.Orders;
import com.example.bhw.Entity.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/showOrders")
public class OrdersServlet extends HttpServlet {

    @Inject
    private OrdersDao ordersDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = ((User) session.getAttribute("user")).getId(); // Assuming user is stored in session
        List<Orders> orders = ordersDao.getOrdersByUserId(userId);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/Orders.jsp").forward(req, resp);
    }
}
