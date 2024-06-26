package com.example.dao.impl;

import com.example.dao.ILoanSlipDAO;
import com.example.mapper.LoanSlipMapper;
import com.example.model.LoanSlipModel;

import java.util.List;

public class LoanSlipDAO extends AbstractDAO<LoanSlipModel> implements ILoanSlipDAO {

    public LoanSlipDAO() {
    }

    @Override
    public List<LoanSlipModel> findALl() {
        String sql = "Select loanSlip.id, code, idAccount, idBook, loanSlip.created_at, loanSlip.updated_at, title, username, phoneNumber from loanSlip, books, account where loanSlip.idBook = books.id and loanSlip.idAccount = account.id ORDER BY loanSlip.created_at DESC";
        List<LoanSlipModel> list = query(sql, new LoanSlipMapper());
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    @Override
    public List<LoanSlipModel> findOneByCode(String id) {
        String sql = "Select loanSlip.id, code, idAccount, idBook, loanSlip.created_at, loanSlip.updated_at, title, username, phoneNumber" +
                " from loanSlip, books, account where loanSlip.idBook = books.id and loanSlip.idAccount = account.id " +
                " and loanSlip.code = ? ORDER BY loanSlip.created_at DESC";
        if (query(sql, new LoanSlipMapper(), id) != null && !query(sql, new LoanSlipMapper(), id).isEmpty()) {
            return query(sql, new LoanSlipMapper(), id);
        }
        return null;
    }

    @Override
    public LoanSlipModel findOneById(int id) {
        String sql = "Select loanSlip.id, code, idAccount, idBook, loanSlip.created_at, loanSlip.updated_at, title, username, phoneNumber" +
                " from loanSlip, books, account where loanSlip.idBook = books.id and loanSlip.idAccount = account.id " +
                " and loanSlip.id = ?" +
                " ORDER BY loanSlip.created_at DESC";
        if (query(sql, new LoanSlipMapper(), id) != null && !query(sql, new LoanSlipMapper(), id).isEmpty())
            return query(sql, new LoanSlipMapper(), id).get(0);
        return null;
    }

    @Override
    public List<LoanSlipModel> searchByCode(String code) {
        String sql = "Select loanSlip.id, code, idAccount, idBook, loanSlip.created_at, loanSlip.updated_at, title, username, phoneNumber" +
                " from loanSlip, books, account where loanSlip.idBook = books.id and loanSlip.idAccount = account.id " +
                " and loanSlip.code like '%" + code + "%' ORDER BY loanSlip.created_at DESC";
        if (query(sql, new LoanSlipMapper()) != null && !query(sql, new LoanSlipMapper()).isEmpty()) {
            return query(sql, new LoanSlipMapper());
        }
        return null;
    }

    @Override
    public List<LoanSlipModel> findByIdLoanSlipAndIdAccount(String idLoanSlip, int idAccount) {
        String sql = "Select loanSlip.id, code, idAccount, idBook, loanSlip.created_at, loanSlip.updated_at, title, username, phoneNumber" +
                " from loanSlip, books, account where loanSlip.idBook = books.id and loanSlip.idAccount = account.id " +
                " and account.id = ? and loanSlip.code = ?" +
                " ORDER BY loanSlip.created_at DESC";
        if (query(sql, new LoanSlipMapper(), idAccount, idLoanSlip) != null && !query(sql, new LoanSlipMapper(), idAccount, idLoanSlip).isEmpty()) {
            return query(sql, new LoanSlipMapper(), idAccount, idLoanSlip);
        }
        return null;
    }

    @Override
    public List<LoanSlipModel> findLoanSlipByQuery(String query) {
        String sql = "Select loanSlip.id, code, idAccount, idBook, loanSlip.created_at, loanSlip.updated_at, title, username, phoneNumber from loanSlip, books, account where loanSlip.idBook = books.id and loanSlip.idAccount = account.id AND code LIKE'" + query + "%' ORDER BY loanSlip.created_at DESC";
        if (query(sql, new LoanSlipMapper()) != null && !query(sql, new LoanSlipMapper()).isEmpty()) {
            return query(sql, new LoanSlipMapper());
        }
        return null;
    }

    @Override
    public boolean isExistBookInLoanSlip(String code, int idBook) {
        String sql = "Select * from loanSlip where code = ? and idBook = ?";
        List<LoanSlipModel> list = query(sql, new LoanSlipMapper(), code, idBook);
        return !list.isEmpty();
    }

    @Override
    public boolean isExistCode(String code) {
        String sql = "Select * from loanslip where code = ?";
        List<LoanSlipModel> list = query(sql, new LoanSlipMapper(), code);
        return !list.isEmpty();
    }

    @Override
    public Long addLoanSlip(LoanSlipModel loanSlipModel) {
        String sql = "INSERT INTO loanslip (code, idAccount, idBook) VALUES (?, ?, ?)";
        return insert(sql, loanSlipModel.getCode(), loanSlipModel.getIdAccount(), loanSlipModel.getIdBook());
    }

    @Override
    public void updateLoanSlip(LoanSlipModel loanSlipModel, int id) {
        String sql = "UPDATE loanslip SET `idAccount` = ?, `idBook` = ? WHERE `id` = ?";
        update(sql, loanSlipModel.getIdAccount(), loanSlipModel.getIdBook(), id);
    }

    @Override
    public void deleteLoanSlip(int id) {
        String sql = "DELETE FROM loanslip WHERE id = ?";
        update(sql, id);
    }
}