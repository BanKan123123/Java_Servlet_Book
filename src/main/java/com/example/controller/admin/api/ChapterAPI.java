package com.example.controller.admin.api;

import com.example.service.impl.ChapterService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-chapters/*"})
public class ChapterAPI extends HttpServlet {

    private final ChapterService chapterService = new ChapterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        chapterService.findData(pathInfo, req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        chapterService.insertData(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        chapterService.update(pathInfo, req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        chapterService.delete(pathInfo, req, resp);
    }
}
