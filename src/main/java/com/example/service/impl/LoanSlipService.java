package com.example.service.impl;

import com.example.dao.impl.AccountDAO;
import com.example.dao.impl.LoanSlipDAO;
import com.example.model.AccountModel;
import com.example.model.LoanSlipModel;
import com.example.service.ILoanSlipService;
import com.example.utils.HttpUtil;
import com.example.utils.ResponseAPIUtils;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoanSlipService implements ILoanSlipService {
    private final LoanSlipDAO loanSlipDAO = new LoanSlipDAO();
    private final AccountDAO accountDAO = new AccountDAO();
    private final ResponseAPIUtils<LoanSlipModel> responseAPIUtils = new ResponseAPIUtils<>();

    @Override
    public List<LoanSlipModel> findAll() {
        return loanSlipDAO.findALl();
    }

    @Override
    public List<LoanSlipModel> findOneByIdLoanSlip(String id) {
        return loanSlipDAO.findOneByCode(id);
    }

    @Override
    public List<LoanSlipModel> searchByCode(String code) {
        return loanSlipDAO.searchByCode(code);
    }

    @Override
    public List<LoanSlipModel> findByIdLoanSlipAndIdAccount(String idLoanSlip, int idAccount) {
        return loanSlipDAO.findByIdLoanSlipAndIdAccount(idLoanSlip, idAccount);
    }

    @Override
    public List<LoanSlipModel> findLoanSlipByQuery(String query) {
        return loanSlipDAO.findLoanSlipByQuery(query);
    }

    @Override
    public LoanSlipModel save(LoanSlipModel loanSlipModel) {
        boolean isExistBook = loanSlipDAO.isExistBookInLoanSlip(loanSlipModel.getCode(), loanSlipModel.getIdBook());
        if (!isExistBook) {
            Long id = loanSlipDAO.addLoanSlip(loanSlipModel);
            return loanSlipDAO.findOneById(Integer.parseInt(id.toString()));
        }
        return null;
    }

    @Override
    public LoanSlipModel update(LoanSlipModel loanSlipModel, int id) {
        boolean isExistBook = loanSlipDAO.isExistBookInLoanSlip(loanSlipModel.getCode(), loanSlipModel.getIdBook());
        if (!isExistBook) {
            loanSlipDAO.updateLoanSlip(loanSlipModel, id);
            return findOneById((long) id);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        loanSlipDAO.deleteLoanSlip(id);
    }

    public void deleteData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        WrapperResponse<LoanSlipModel> wrapperResponse = new WrapperResponse<>();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("delete")) {
                    int id = Integer.parseInt(path[2]);
                    LoanSlipModel findLoanSlip = findOneById((long) id);
                    if (findLoanSlip != null) {
                        delete(id);
                        responseAPIUtils.deleteSuccess(wrapperResponse, resp);
                    } else {
                        responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                    }
                } else {
                    responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                }
            }
        } else {
            responseAPIUtils.notFoundAPI(wrapperResponse, resp);
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void updateData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        WrapperResponse<LoanSlipModel> wrapperResponse = new WrapperResponse<>();

        LoanSlipModel loanSlipModel = HttpUtil.of(req.getReader()).toModel(LoanSlipModel.class);
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("update")) {
                    int id = Integer.parseInt(path[2]);
                    LoanSlipModel saveLoanSlip = update(loanSlipModel, id);
                    if (saveLoanSlip != null) {
                        ArrayList<LoanSlipModel> list = new ArrayList<>();
                        list.add(saveLoanSlip);
                        responseAPIUtils.updateSuccess(wrapperResponse, list, resp);
                    } else {
                        responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
                    }
                }
            } else {
                responseAPIUtils.ServerError(wrapperResponse, resp);
            }
        } else {
            responseAPIUtils.notFoundAPI(wrapperResponse, resp);
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void insertData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        WrapperResponse<LoanSlipModel> wrapperResponse = new WrapperResponse<>();
        LoanSlipModel loanSlipModel = HttpUtil.of(req.getReader()).toModel(LoanSlipModel.class);
        if (loanSlipModel.getIdBook() == 0 || loanSlipModel.getCode() == null || loanSlipModel.getCode().isEmpty() || loanSlipModel.getIdAccount() == 0) {
            responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
        } else {
            LoanSlipModel findLoanSlip = findOneById(Long.valueOf(loanSlipModel.getId() + ""));
            if (findLoanSlip == null) {
                LoanSlipModel saveLoanSlip = save(loanSlipModel);
                if (saveLoanSlip == null) {
                    responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
                } else {
                    ArrayList<LoanSlipModel> list = new ArrayList<>();
                    list.add(saveLoanSlip);
                    responseAPIUtils.insertSuccess(wrapperResponse, list, resp);
                }
            } else {
                responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
            }
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public LoanSlipModel findOneById(Long id) {
        LoanSlipModel loanSlipModel = loanSlipDAO.findOneById(Integer.parseInt(id.toString()));
        if (loanSlipModel != null) {
            AccountModel accountModel = accountDAO.findOneById(loanSlipModel.getIdAccount() + "");
            loanSlipModel.setUserName(accountModel.getUsername());
            loanSlipModel.setNumberPhone(accountModel.getPhoneNumber());
            return loanSlipModel;
        } else {
            return null;
        }
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        ArrayList<LoanSlipModel> list = new ArrayList<>();
        WrapperResponse<LoanSlipModel> wrapperResponse = new WrapperResponse<>();

        if (pathInfo == null || pathInfo.equals("/")) {
            String code = req.getParameter("search");
            if (code != null) {
                list = (ArrayList<LoanSlipModel>) searchByCode(code);
            } else {
                list = (ArrayList<LoanSlipModel>) findAll();
            }
        } else {
            String[] path = pathInfo.split("/");
            if (path.length == 5) {
                String idLoanSlipStr = path[2];
                String idAccountStr = path[4];

                int idAccount = 0;
                try {
                    idAccount = Integer.parseInt(idAccountStr);
                } catch (NumberFormatException ex) {
                    responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                }
                list = (ArrayList<LoanSlipModel>) findByIdLoanSlipAndIdAccount(idLoanSlipStr, idAccount);
            } else if (path.length == 3) {
                String idLoanSlipStr = path[2];
                list = (ArrayList<LoanSlipModel>) findOneByIdLoanSlip(idLoanSlipStr);
            }
        }
        if (list != null && !list.isEmpty()) {
//            int length = list.size();
            for (LoanSlipModel loanSlipModel : list) {
                String id = loanSlipModel.getIdAccount() + "";
                AccountModel accountModel = accountDAO.findOneById(id);
                loanSlipModel.setUserName(accountModel.getUsername());
                loanSlipModel.setNumberPhone(accountModel.getPhoneNumber());
            }
            responseAPIUtils.getDataSuccess(wrapperResponse, list, resp);
        }
//        } else {
//            responseAPIUtils.notFoundAPI(wrapperResponse, resp);
//        }


        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }
}
