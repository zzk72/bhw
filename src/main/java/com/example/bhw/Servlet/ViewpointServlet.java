package com.example.bhw.Servlet;

import com.example.bhw.Bean.QueueStatusBean;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("viewQueueStatus".equals(action)) {
            handleViewQueueStatus(req, resp);
        } else if ("showHome".equals(action)) {
            List<Viewpoint> viewpoints = queueStatusBean.getViewpoints();
            req.setAttribute("viewpoints", viewpoints);
            req.getRequestDispatcher("/viewpoints.jsp").forward(req, resp);
        } else if ("viewServices".equals(action)) {
            handleViewServices(req, resp);
        }
    }

    private void handleViewQueueStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Viewpoint> viewpoints = queueStatusBean.getViewpoints();
        for (Viewpoint viewpoint : viewpoints) {
            long queueStatus = queueStatusBean.getQueueStatus(viewpoint);
            viewpoint.setQueueStatus(queueStatus);
        }
        request.setAttribute("viewpoints", viewpoints);
        request.getRequestDispatcher("/queueStatus.jsp").forward(request, response);
    }

    private void handleViewServices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int viewpointId = Integer.parseInt(request.getParameter("viewpointId")); // todo: 景区id没有传过来
        List<Service> services = queueStatusBean.getServicesByViewpointId(viewpointId);
        request.setAttribute("services", services);
        request.getRequestDispatcher("/services.jsp").forward(request, response);
    }
}
