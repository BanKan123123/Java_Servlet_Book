package com.example.service;

import com.example.model.LoanSlipModel;

import java.util.List;

public interface ILoanSlipService {
    List<LoanSlipModel> findAll();
    List<LoanSlipModel> findOneByIdLoanSlip(String id);
    List<LoanSlipModel> findByIdLoanSlipAndIdAccount(String idLoanSlip, int idAccount);
    List<LoanSlipModel> findLoanSlipByQuery(String query);
    LoanSlipModel save (LoanSlipModel loanSlipModel);
    LoanSlipModel update(LoanSlipModel loanSlipModel, int id);
    void delete (int id);
}
