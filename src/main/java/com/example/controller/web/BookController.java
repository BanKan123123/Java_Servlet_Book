package com.example.controller.web;

import com.example.apihandler.GenericHandleAPI;
import com.example.model.BookModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
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

@WebServlet(urlPatterns = {"/book/*", "/book"})
public class BookController extends HttpServlet {
    private final GenericHandleAPI<BookModel> genericHandleAPI = new GenericHandleAPI<>();
    private String urlAPI;

    @Override
    public void init() {
        urlAPI = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-books";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = "/views/admin/book.jsp";
        genericHandleAPI.getAPIHandle(urlAPI, pathInfo, path, resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();

        String title = req.getParameter("title");
        String replaced = title.toLowerCase().replaceAll("\\s+", "-");
        String slug = replaced.replaceAll("[^a-z0-9-]", "");
        String description = req.getParameter("description");
        String imageThumbnail = req.getParameter("image-thumbnail");
        float rate = Float.parseFloat(req.getParameter("rate"));
        int liked = Integer.parseInt(req.getParameter("liked"));
        int authorId = Integer.parseInt(req.getParameter("author-id"));
        String categories = req.getParameter("categories");
        int quantity = Integer.parseInt("quantity");

        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("slug", slug);
        object.addProperty("description", description);
        object.addProperty("imageThumbnail", imageThumbnail);
        object.addProperty("rate", rate);
        object.addProperty("liked", liked);
        object.addProperty("authorId", authorId);
        object.addProperty("categories", categories);
        object.addProperty("quantity", quantity);

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
            mapper.writeValue(resp.getOutputStream(), responseBuilder);
        }
        connection.disconnect();
    }
}
