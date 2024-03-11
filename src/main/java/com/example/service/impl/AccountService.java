package com.example.service.impl;

import com.example.dao.impl.AccountDAO;
import com.example.model.AccountModel;
import com.example.service.IAccountService;
import com.example.utils.HttpUtil;
import com.example.utils.ResponseAPIUtils;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {

    private final AccountDAO accountDAO = new AccountDAO();
    private final ResponseAPIUtils<AccountModel> responseAPIUtils = new ResponseAPIUtils<>();

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

        WrapperResponse<AccountModel> wrapperResponse = new WrapperResponse<>();

        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<AccountModel> listAccount = (ArrayList<AccountModel>) findAllAccount();
            responseAPIUtils.getDataSuccess(wrapperResponse, listAccount, resp);
        } else {
            String username = pathInfo.substring(1);
            AccountModel accountModel = findOneAccount(username);
            if (accountModel != null) {
                ArrayList<AccountModel> accounts = new ArrayList<>();
                accounts.add(accountModel);
                responseAPIUtils.getDataSuccess(wrapperResponse, accounts, resp);
            } else {
                responseAPIUtils.notFoundAPI(wrapperResponse, resp);
            }
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void registerAccount(AccountModel accountModel, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        WrapperResponse<AccountModel> wrapperResponse = new WrapperResponse<>();
        if (accountModel.getUsername() == null || accountModel.getUsername().isEmpty() || accountModel.getPassword() == null || accountModel.getPassword().isEmpty() || accountModel.getEmail() == null || accountModel.getEmail().isEmpty()) {
            responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
        } else {
            AccountModel findAccount = findOneAccount(accountModel.getUsername());
            if (findAccount == null) {
                AccountModel accountModel1 = save(accountModel);
                ArrayList<AccountModel> accounts = new ArrayList<>();
                accounts.add(accountModel1);
                responseAPIUtils.insertSuccess(wrapperResponse, accounts, resp);
            } else {
                responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
            }
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void account(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        WrapperResponse<AccountModel> wrapperResponse = new WrapperResponse<>();
        AccountModel accountModel = HttpUtil.of(req.getReader()).toModel(AccountModel.class);
        if (pathInfo == null || pathInfo.equals("/")) {
            responseAPIUtils.ServerError(wrapperResponse, resp);
        } else {
            String reqValue = pathInfo.substring(1);
            if (reqValue.equals("register")) {
                this.registerAccount(accountModel, req, resp);
            } else if (reqValue.equals("login")) {
                if (accountModel.getUsername() == null || accountModel.getUsername().isEmpty() || accountModel.getPassword() == null || accountModel.getPassword().isEmpty()) {
                    responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
                } else {
                    AccountModel accountModel3 = login(accountModel.getUsername(), accountModel.getPassword());
                    if (accountModel3 == null) {
                        responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                    } else {
                        ArrayList<AccountModel> accounts = new ArrayList<>();
                        accounts.add(accountModel3);
                        responseAPIUtils.getDataSuccess(wrapperResponse, accounts, resp);
                    }
                }
            } else {
                responseAPIUtils.ServerError(wrapperResponse, resp);
            }
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }
}