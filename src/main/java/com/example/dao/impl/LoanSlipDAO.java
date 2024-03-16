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
        String sql = "Select DISTINCT *  from books, author, category , categoriesonbook, loanSlip where books.id = categoriesonbook.bookId AND category.id = categoriesonbook.categoryId AND books.authorId = author.id AND books.id = loanSlip.idBook ORDER BY loanSlip.created_at DESC";
        List<LoanSlipModel> list = query(sql, new LoanSlipMapper());
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<LoanSlipModel> findOneByCode(String id) {
        String sql = "Select DISTINCT *" +
                " from books, author, category , categoriesonbook, loanSlip" +
                " where books.id = categoriesonbook.bookId AND category.id = categoriesonbook.categoryId AND books.authorId = author.id AND books.id = loanSlip.idBook AND loanSlip.code = ?" +
                " ORDER BY loanSlip.created_at DESC";
        return query(sql, new LoanSlipMapper(), id);
    }

    @Override
    public LoanSlipModel findOneById(int id) {
        String sql = "Select *" +
                " from books, author, category , categoriesonbook, loanSlip" +
                " where books.id = categoriesonbook.bookId AND category.id = categoriesonbook.categoryId AND books.authorId = author.id AND books.id = loanSlip.idBook AND loanSlip.id = ?" +
                " ORDER BY loanSlip.created_at DESC";
        if (query(sql, new LoanSlipMapper(), id).isEmpty()) return null;
        return query(sql, new LoanSlipMapper(), id).get(0);
    }

    @Override
    public List<LoanSlipModel> findByIdLoanSlipAndIdAccount(String idLoanSlip, int idAccount) {
        String sql = "Select *" +
                " from books, author, category , categoriesonbook, loanSlip, account" +
                " where books.id = categoriesonbook.bookId AND category.id = categoriesonbook.categoryId AND books.authorId = author.id AND books.id = loanSlip.idBook AND loanSlip.idAccount = account.id and account.id = ? and loanSlip.code = ?" +
                " ORDER BY loanSlip.created_at DESC";
        return query(sql, new LoanSlipMapper(), idAccount, idLoanSlip);
    }

    @Override
    public boolean isExistBookInLoanSlip(String code, int idBook) {
        String sql = "Select * from loanSlip where code = ? and idBook = ?";
        List<LoanSlipModel> list = query(sql, new LoanSlipMapper(), code, idBook);
        return list.isEmpty();
    }

    @Override
    public Long addLoanSlip(LoanSlipModel loanSlipModel) {
        String sql = "INSERT INTO loanSlip (code, idAccount, idBook) VALUES (?, ?, ?)";
        return insert(sql, loanSlipModel.getCode(), loanSlipModel.getIdAccount(), loanSlipModel.getIdBook());
    }

    @Override
    public void updateLoanSlip(LoanSlipModel loanSlipModel, int id) {
        String sql = "UPDATE loanSlip SET `idAccount` = ?, `idBook` = ? WHERE `id` = ?";
        update(sql, loanSlipModel.getIdAccount(), loanSlipModel.getIdBook(), id);
    }

    @Override
    public void deleteLoanSlip(int id) {
        String sql = "DELETE FROM loanSlip WHERE id = ?";
        update(sql, id);
    }
}
