package com.example.bhw.Servlet;

import com.example.bhw.Bean.ReservationBean;
import com.example.bhw.Dao.ViewpointDao;
import com.example.bhw.Entity.User;
import com.example.bhw.Entity.Viewpoint;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/reserve")
public class ReservationServlet extends HttpServlet {

    @Inject
    private ReservationBean reservationBean;

    @Inject
    private ViewpointDao viewpointDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int viewpointId = Integer.parseInt(req.getParameter("viewpointId"));
        LocalDateTime reservationTime = LocalDateTime.parse(req.getParameter("reservationTime"));

        User user = (User) req.getSession().getAttribute("user");
        Viewpoint viewpoint = viewpointDao.getViewpoint(viewpointId);

        reservationBean.reserveViewpoint(user, viewpoint, reservationTime);

        resp.sendRedirect("reservation_success.jsp");
    }
}
