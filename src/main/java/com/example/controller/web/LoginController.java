package com.example.controller.web;

import com.example.model.AccountModel;
import com.example.service.impl.AccountService;
import com.example.utils.FormUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    public void init() {
        String message = "Username or password is invalid";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rq = req.getRequestDispatcher("/views/web/login.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String action = req.getParameter("action");
        ObjectMapper objectMapper = new ObjectMapper();

        if (action != null && action.equals("login")) {
            AccountModel accountModel = FormUtils.toModel(AccountModel.class, req);
            AccountModel accountModel1 = accountService.login(accountModel.getUsername(), accountModel.getPassword());
            if (accountModel1 == null) {
                resp.sendRedirect(req.getContextPath() + "/login/?action=login&status=false");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        }
    }
}
