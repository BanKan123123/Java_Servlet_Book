package com.example.service.impl;

import com.example.dao.impl.AccountDAO;
import com.example.model.AccountModel;
import com.example.service.IAccountService;
import com.example.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {

    private final AccountDAO accountDAO = new AccountDAO();

    @Override
    public List<AccountModel> findAllAccount() {
        return accountDAO.findAllAccount();
    }

    @Override
    public AccountModel findOneAccount(String username) {
        return accountDAO.findOneAccount(username);
    }

    @Override
    public AccountModel login(String username, String password) {
        return accountDAO.signInAccount(username, password);
    }

    @Override
    public AccountModel save(AccountModel accountModel) {
        accountDAO.registerAccount(accountModel);
        return findOneAccount(accountModel.getUsername());
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<AccountModel> listAccount = (ArrayList<AccountModel>) findAllAccount();
            mapper.writeValue(resp.getOutputStream(), listAccount);
        } else {
            String username = pathInfo.substring(1);
            AccountModel accountModel = findOneAccount(username);
            if (accountModel != null) {
                mapper.writeValue(resp.getOutputStream(), accountModel);
            } else {
                resp.setStatus(404);
            }
        }
    }

    public void registerAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8"); // Để khi mà thằng client nó có chuỗi tiếng việt
        resp.setContentType("application/json"); // Trả kết quả cho thằng client, và cụ thể là trả về json, thì thằng server định nghĩa một header để thằng Client hiểu.
        AccountModel accountModel = HttpUtil.of(req.getReader()).toModel(AccountModel.class);
        AccountModel accountModel1 = save(accountModel);
        mapper.writeValue(resp.getOutputStream(), accountModel1);
    }

    public void account(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        AccountModel accountModel = HttpUtil.of(req.getReader()).toModel(AccountModel.class);
        if (pathInfo == null || pathInfo.equals("/")) {
            mapper.writeValue(resp.getOutputStream(), null);
        } else {
            String reqValue = pathInfo.substring(1);
            if (reqValue.equals("register") && accountModel.getEmail() != null) {
                AccountModel accountModel1 = findOneAccount(accountModel.getUsername());
                if (accountModel1 != null) {
                    String error = "Error: Tai khoan da ton tai";
                    mapper.writeValue(resp.getOutputStream(), error);
                    resp.setStatus(409);
                } else {
                    AccountModel accountModel2 = save(accountModel);
                    mapper.writeValue(resp.getOutputStream(), accountModel2);
                    resp.setStatus(200);
                }
            } else if (reqValue.equals("login")) {
                AccountModel accountModel3 = login(accountModel.getUsername(), accountModel.getPassword());
                mapper.writeValue(resp.getOutputStream(), accountModel3);
                resp.setStatus(200);
            }
        }
    }
}