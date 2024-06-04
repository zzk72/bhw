package com.example.bhw.Servlet;

import com.example.bhw.Bean.HandleWebServiceBean;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@WebServlet("/viewpoints")
public class ViewpointServlet extends HttpServlet {

    @Inject
    private QueueStatusBean queueStatusBean;

    @Inject
    private ViewpointDao viewpointDao;
    @Inject
    HandleWebServiceBean handleWebServiceBean;

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

        //通过RESTFul服务获取第三方评价信息
        List<String> reviews = handleWebServiceBean.handleViewReviews(viewpointId);
        request.setAttribute("reviews", reviews);

        //通过RESTFul服务获取天气信息
        String weatherInfo = handleWebServiceBean.handleViewWeather("2021-06-01",viewpointId);
        request.setAttribute("weatherInfo", weatherInfo);

        //通过RESTFul服务获取空气质量信息
        String airInfo = handleWebServiceBean.handleViewAir("2021-06-01",viewpointId);
        request.setAttribute("airQuality", airInfo);

        //通过RESTFul服务获取温度信息
        String temperature = handleWebServiceBean.handleViewTemperature("2021-06-01",viewpointId);
        request.setAttribute("temperature", temperature);

        request.getRequestDispatcher("/detailInfo.jsp").forward(request, response);
    }


}
