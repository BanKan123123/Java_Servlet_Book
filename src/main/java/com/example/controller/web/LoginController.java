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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    private String message;

    public void init() {
        message = "Username or password is invalid";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            String status = req.getParameter("status");
            if (status != null && status.equals("false")) {
                req.setAttribute("message", message);
                req.setAttribute("alert", "danger");
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (action != null && action.equals("login")) {
            AccountModel accountModel = FormUtils.toModel(AccountModel.class, req);
            AccountModel accountModel1 = accountService.login(accountModel.getUsername(), accountModel.getPassword());
            if (accountModel1 == null) {
                resp.sendRedirect(req.getContextPath() + "/login?action=login&status=false");
            } else {
                session.setAttribute("username", accountModel1.getUsername());
                resp.sendRedirect(req.getContextPath() + "/book");
            }
        }
    }
}
