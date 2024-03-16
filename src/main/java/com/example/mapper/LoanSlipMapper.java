package com.example.mapper;

import com.example.model.BookModel;
import com.example.model.LoanSlipModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanSlipMapper implements RowMapper<LoanSlipModel>{
    @Override
    public LoanSlipModel mapRow(ResultSet rs) {
        try {

            BookModel bookModel = new BookMapper().mapRow(rs);

            LoanSlipModel loanSlipModel = new LoanSlipModel();
            loanSlipModel.setBookModel(bookModel);
            loanSlipModel.setId((int) rs.getLong(22));
            loanSlipModel.setCode(rs.getString(23));
            loanSlipModel.setIdAccount((int) rs.getLong(24));
            loanSlipModel.setIdBook((int) rs.getLong(24));
            loanSlipModel.setCreated_at(rs.getTimestamp(26));
            loanSlipModel.setUpdated_at(rs.getTimestamp(27));

            return loanSlipModel;
        }catch (SQLException e) {
            return null;
        }
    }
}
