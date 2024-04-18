package com.example.dao;

import com.example.model.LoanSlipModel;

import java.util.List;

public interface ILoanSlipDAO extends GenericDAO<LoanSlipModel> {
    List<LoanSlipModel> findALl();

    List<LoanSlipModel> findOneByCode(String id);

    LoanSlipModel findOneById(int id);

    List<LoanSlipModel> searchByCode (String code);

    List<LoanSlipModel> findByIdLoanSlipAndIdAccount(String idLoanSlip, int idAccount);

    List<LoanSlipModel> findLoanSlipByQuery(String query);

    boolean isExistCode(String code);

    boolean isExistBookInLoanSlip(String code, int idBook);

    //    boolean isExistCode (String code);
    Long addLoanSlip(LoanSlipModel loanSlipModel);

    void updateLoanSlip(LoanSlipModel loanSlipModel, int id);

    void deleteLoanSlip(int id);
}
