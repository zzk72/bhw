package com.example.bhw.Servlet;

import com.example.bhw.Bean.QueueStatusBean;
import com.example.bhw.Dao.ViewpointDao;
import com.example.bhw.Entity.Service;
import com.example.bhw.Entity.Viewpoint;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewpoints")
public class ViewpointServlet extends HttpServlet {

    @Inject
    private QueueStatusBean queueStatusBean;

    @Inject
    private ViewpointDao viewpointDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("showHome".equals(action)) {
            List<Viewpoint> viewpoints = queueStatusBean.getViewpoints();
            req.setAttribute("viewpoints", viewpoints);
            req.getRequestDispatcher("/viewpoints.jsp").forward(req, resp);
        } else if ("viewServices".equals(action)) {
            handleViewServices(req, resp);
        }
    }


    private void handleViewServices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int viewpointId = Integer.parseInt(request.getParameter("viewpointId"));
        Viewpoint viewpoint = viewpointDao.getViewpointById(viewpointId);
        long queueStatus = queueStatusBean.getQueueStatus(viewpoint);
        viewpoint.setQueueStatus(queueStatus);
        request.setAttribute("viewpoint", viewpoint);
        List<Service> services = queueStatusBean.getServicesByViewpointId(viewpointId);
        request.setAttribute("services", services);
        request.getRequestDispatcher("/services.jsp").forward(request, response);
    }
}
