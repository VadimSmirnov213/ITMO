package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.PointDTO;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "AreaCheckServlet", urlPatterns = "/area-check")
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        if (!"GET".equals(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "только GET");
            return;
        }
        super.service(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        if (request.getDispatcherType() != DispatcherType.FORWARD) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        long startTime = System.nanoTime();
        
        try {
            double x = Double.parseDouble(request.getParameter("x"));
            double y = Double.parseDouble(request.getParameter("y"));
            double r = Double.parseDouble(request.getParameter("r"));

            boolean hit = checkPointInArea(x, y, r);
            
            long executionTime = (System.nanoTime() - startTime) / 1000;
            
            PointDTO result = new PointDTO(x, y, r, hit, executionTime);
            
            saveResult(result);
            
            response.sendRedirect("controller");
            
        } catch (NumberFormatException e) {
            response.sendRedirect("controller?error=format");
        } catch (Exception e) {
            response.sendRedirect("controller?error=general");
        }
    }

    private boolean checkPointInArea(double x, double y, double r) {
        if (x <= 0 && y >= 0) {
            return (x * x + y * y) <= (r * r / 4);
        }
        
        if (y <= 0 && y >= x - r/2 && x >= -r/2 && x <= r/2) {
            return true;
        }
        
        if (x >= 0 && y <= 0 && x <= r/2 && y >= -r) {
            return true;
        }
        
        return false;
    }

    @SuppressWarnings("unchecked")
    private void saveResult(PointDTO result) {
        List<PointDTO> results = (List<PointDTO>) getServletContext().getAttribute("checkResults");
        if (results == null) {
            results = new CopyOnWriteArrayList<>();
            getServletContext().setAttribute("checkResults", results);
        }
        results.add(result);
        
        if (results.size() > 100) {
            results.remove(0);
        }
    }

}