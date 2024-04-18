package com.example.controller.web;

import com.example.apihandler.GenericHandleAPI;
import com.example.model.AccountModel;
import com.example.model.BookModel;
import com.example.model.LoanSlipModel;
import com.example.paramMapper.LoanSlipParamMapper;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/loan-slip"})
public class LoanSlipController extends HttpServlet {
    private final GenericHandleAPI<LoanSlipModel> genericHandleAPI = new GenericHandleAPI<>();
    private final GenericHandleAPI<BookModel> genericHandleAPIBook = new GenericHandleAPI<>();
    private final GenericHandleAPI<AccountModel> genericHandleAPIAccount = new GenericHandleAPI<>();

    private String urlAPILoanSlip, urlAPIBook, urlAPIAccount;

    @Override
    public void init() {
        urlAPILoanSlip = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-loan-slip";
        urlAPIBook = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-books";
        urlAPIAccount = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-account/get-users";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = "/views/admin/loanSlip.jsp";
        WrapperResponse<LoanSlipModel> responseLoanSlip;
        WrapperResponse<BookModel> responseBook = genericHandleAPIBook.getMultipleAPIHandle(urlAPIBook, pathInfo);
        WrapperResponse<AccountModel> responseAccount = genericHandleAPIAccount.getMultipleAPIHandle(urlAPIAccount, pathInfo);
        if (req.getParameter("search") != null && !req.getParameter("search").isEmpty()) {
            String pathSearch = urlAPILoanSlip + "?search=" + req.getParameter("search");
            responseLoanSlip = genericHandleAPI.getMultipleAPIHandle(pathSearch, pathInfo);
        } else {
            responseLoanSlip = genericHandleAPI.getMultipleAPIHandle(urlAPILoanSlip, pathInfo);
        }
        req.setAttribute("responseLoanSlip", responseLoanSlip.getData());
        req.setAttribute("responseBook", responseBook.getData());
        req.setAttribute("responseAccount", responseAccount.getData());
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        if(action != null) {
            if(action.equals("update")) {
                doPut(req, resp);
                resp.sendRedirect(req.getContextPath() +"/loan-slip");
            }else if(action.equals("delete")) {
                doDelete(req, resp);
                resp.sendRedirect(req.getContextPath() +"/loan-slip");
            }
        } else {
            String jsonLoanSlip = LoanSlipParamMapper.mapperParam(req);
            genericHandleAPI.postAPIHandle(urlAPILoanSlip, jsonLoanSlip, resp, mapper);
            resp.sendRedirect(req.getContextPath() +"/loan-slip");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String urlAPI =  "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-loan-slip/" + action + "/" + id;
        String jsonLoanSlip = LoanSlipParamMapper.mapperParam(req);
        genericHandleAPI.putAPIHandel(urlAPI, jsonLoanSlip, resp, mapper);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String urlAPI = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-loan-slip/" +action + "/" + id;
        genericHandleAPI.deleteAPIHandel(urlAPI, resp, mapper);
    }
}
