package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.JwtUtil;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HeadersUsageServlet", urlPatterns = "/headers")
public class HeadersUsageServlet extends HttpServlet {
    private final JwtUtil jwtUtil = new JwtUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null || authHeader.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"error\":\"Authorization header is missing. Please provide JWT token.\"}");
            }
            return;
        }

        if (!jwtUtil.isTokenValid(authHeader)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"error\":\"Invalid or expired token.\"}");
            }
            return;
        }

        if (!jwtUtil.isAdmin(authHeader)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"error\":\"Access denied. Admin role required.\"}");
            }
            return;
        }

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("{");
            boolean first = true;
            for (String headerName : java.util.Collections.list(request.getHeaderNames())) {
                if (!first) out.print(",");
                out.print("\"");
                out.print(escapeJson(headerName));
                out.print("\":\"");
                out.print(escapeJson(request.getHeader(headerName)));
                out.print("\"");
                first = false;
            }
            out.print("}");
        }
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "\\r");
    }
}