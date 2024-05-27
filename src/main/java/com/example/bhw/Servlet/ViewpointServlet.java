package com.example.bhw.Servlet;

import com.example.bhw.Dao.ViewpointDao;
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
    private ViewpointDao viewpointDao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Viewpoint> viewpoints = viewpointDao.findAll();
        req.setAttribute("viewpoints", viewpoints);
        req.getRequestDispatcher("/viewpoints.jsp").forward(req, resp);
    }
}
