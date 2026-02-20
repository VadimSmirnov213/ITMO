package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.JwtUtil;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "LoginTokenServlet", urlPatterns = "/login-token")
public class LoginTokenServlet extends HttpServlet {
    private final JwtUtil jwtUtil = new JwtUtil();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("application/json;charset=UTF-8");

        if (username == null || username.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"error\":\"Username is required\"}");
            }
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"error\":\"Password is required\"}");
            }
            return;
        }

        String token;
        String role;
        if ("admin".equals(password)) {
            token = jwtUtil.generateAdminToken(username);
            role = "admin";
        } else {
            token = jwtUtil.generateUserToken(username);
            role = "user";
        }

        response.setStatus(HttpServletResponse.SC_OK);
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"token\":\"");
            out.print(token);
            out.print("\",\"role\":\"");
            out.print(role);
            out.print("\",\"username\":\"");
            out.print(escapeJson(username));
            out.print("\"}");
        }
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "\\r");
    }
}
