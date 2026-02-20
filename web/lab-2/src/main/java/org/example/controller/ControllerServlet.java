package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.PointDTO;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControllerServlet", urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String xParam = request.getParameter("x");
        String yParam = request.getParameter("y");
        String rParam = request.getParameter("r");
        String errorParam = request.getParameter("error");
        
        if (errorParam != null) {
            String errorMessage = "";
            if ("format".equals(errorParam)) {
                errorMessage = "Ошибка: неверный формат параметров";
            } else if ("general".equals(errorParam)) {
                errorMessage = "Ошибка при обработке запроса";
            }
            request.setAttribute("errorMessage", errorMessage);
        }
        
        if (xParam != null && yParam != null && rParam != null && 
            !xParam.trim().isEmpty() && !yParam.trim().isEmpty() && !rParam.trim().isEmpty()) {
            
            request.getRequestDispatcher("/area-check").forward(request, response);
        } else {
            @SuppressWarnings("unchecked")
            List<PointDTO> results = 
                (List<PointDTO>) request.getServletContext().getAttribute("checkResults");
            
            if (results != null) {
                request.setAttribute("results", results);
            }
            
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
