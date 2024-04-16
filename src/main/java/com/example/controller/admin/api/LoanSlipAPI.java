package com.example.controller.admin.api;

import com.example.service.impl.LoanSlipService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-loan-slip", "/api-admin-loan-slip/*"})
public class LoanSlipAPI extends HttpServlet {

    private final LoanSlipService loanSlipService = new LoanSlipService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        loanSlipService.findData(pathInfo, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        loanSlipService.insertData(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        loanSlipService.deleteData(pathInfo, req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        loanSlipService.updateData(pathInfo, req, resp);
    }
}
