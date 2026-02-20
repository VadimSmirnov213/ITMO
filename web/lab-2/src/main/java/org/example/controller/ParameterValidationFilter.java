package org.example.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;


@WebFilter(filterName = "ParameterValidationFilter", urlPatterns = {"/area-check"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class ParameterValidationFilter implements Filter {

    private static final double MIN_X = -4.0;
    private static final double MAX_X = 4.0;
    private static final double MIN_Y = -3.0;
    private static final double MAX_Y = 5.0;
    private static final double MIN_R = 0;
    private static final double MAX_R = 4.0;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String xStr = req.getParameter("x");
        String yStr = req.getParameter("y");
        String rStr = req.getParameter("r");

        resp.setContentType("application/json;charset=UTF-8");

        if (xStr == null || yStr == null || rStr == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = resp.getWriter()) {
                out.print("{\"error\":\"Missing required parameter(s): x, y, r\"}");
            }
            return;
        }

        BigDecimal xBD, yBD, rBD;
        try {
            xBD = new BigDecimal(xStr);
            yBD = new BigDecimal(yStr);
            rBD = new BigDecimal(rStr);
        } catch (NumberFormatException | NullPointerException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = resp.getWriter()) {
                out.print("{\"error\":\"Invalid number format for x, y or r\"}");
            }
            return;
        }

        BigDecimal minXBD = BigDecimal.valueOf(MIN_X);
        BigDecimal maxXBD = BigDecimal.valueOf(MAX_X);
        BigDecimal minYBD = BigDecimal.valueOf(MIN_Y);
        BigDecimal maxYBD = BigDecimal.valueOf(MAX_Y);
        BigDecimal minRBD = BigDecimal.valueOf(MIN_R);
        BigDecimal maxRBD = BigDecimal.valueOf(MAX_R);

        if (xBD.compareTo(minXBD) < 0 || xBD.compareTo(maxXBD) > 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = resp.getWriter()) {
                out.print(String.format("{\"error\":\"Parameter x is out of allowed range [%s..%s]\"}", MIN_X, MAX_X));
            }
            return;
        }

        if (yBD.compareTo(minYBD) <= 0 || yBD.compareTo(maxYBD) >= 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = resp.getWriter()) {
                out.print(String.format("{\"error\":\"Parameter y is out of allowed range (%s..%s)\"}", MIN_Y, MAX_Y));
            }
            return;
        }

        if (rBD.compareTo(minRBD) <= 0 || rBD.compareTo(maxRBD) > 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = resp.getWriter()) {
                out.print(String.format("{\"error\":\"Parameter r must be in range (%s..%s]\"}", MIN_R, MAX_R));
            }
            return;
        }

       
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
      
    }
}
