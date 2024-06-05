package com.example.bhw.JMSClient;

import com.example.bhw.Dao.OrdersDao;
import com.example.bhw.Dao.UserDao;
import com.example.bhw.Dao.ViewpointDao;
import com.example.bhw.Entity.Orders;
import com.example.bhw.Entity.Viewpoint;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
@WebServlet("/checkin")
public class CheckinServlet extends HttpServlet {

    @Inject
    private MessageProducerBean messageProducer;

    @Inject
    UserDao userDao;
    @Inject
    ViewpointDao viewpointDao;
    @Inject
    OrdersDao ordersDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<Viewpoint> viewpointList = viewpointDao.getAllViewpoints();
        req.getSession().setAttribute("viewpointList", viewpointList);
        if ("checkin".equals(action)) {
            String name = req.getParameter("username");
            int userId = userDao.getUserByName(name).getId();
            int viewpointId = Integer.parseInt(req.getParameter("viewpointId"));
            LocalDateTime currentTime = LocalDateTime.now();

            Orders order= ordersDao.getEarliestOrderAfterCurrentTime(userId, viewpointId,currentTime);
            int orderId= -1;
            if(order != null){
                orderId = order.getId();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", userId);
            jsonObject.put("time", currentTime);
            jsonObject.put("viewpointId", viewpointId);
            jsonObject.put("orderId", orderId);
            messageProducer.sendMessage(jsonObject.toString());
            req.getSession().setAttribute("Info","send:"+jsonObject.toString());
            req.getRequestDispatcher("checkin.jsp").forward(req, resp);

        } else {
            req.getSession().setAttribute("Info", "init");
            req.getRequestDispatcher("checkin.jsp").forward(req, resp);
        }

    }
}
