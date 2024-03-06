package com.example.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    String urlAPI = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-account/register";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rq = req.getRequestDispatcher("/views/web/register-page.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
//        String phoneNumber = req.getParameter("phone-number");

        JsonObject object = new JsonObject();
        object.addProperty("username", username);
        object.addProperty("password", password);
        object.addProperty("email", email);
//        object.addProperty("phoneNumber", phoneNumber);

        URL url = new URL(urlAPI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = object.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder responseBuilder = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                responseBuilder.append(responseLine.trim());
            }
            if (responseBuilder == null) {

            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        }

        connection.disconnect();
    }
}
