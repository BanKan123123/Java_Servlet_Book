package com.example.dao;

import com.example.model.LoanSlipModel;

import java.util.List;

public interface ILoanSlipDAO extends GenericDAO<LoanSlipModel> {
    List<LoanSlipModel> findALl();
    List<LoanSlipModel> findOneByCode(String id);
    LoanSlipModel findOneById(int id);
    List<LoanSlipModel> findByIdLoanSlipAndIdAccount(String idLoanSlip, int idAccount);
    boolean isExistBookInLoanSlip (String code, int idBook);
//    boolean isExistCode (String code);
    Long addLoanSlip(LoanSlipModel loanSlipModel);
    void updateLoanSlip(LoanSlipModel loanSlipModel, int id);
    void deleteLoanSlip(int id);
}
