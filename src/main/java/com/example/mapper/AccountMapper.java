package com.example.mapper;

import com.example.model.AccountModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<AccountModel> {
    @Override
    public AccountModel mapRow(ResultSet resultSet) {
        try {
            AccountModel accountModel = new AccountModel();

            accountModel.setId(resultSet.getInt("id"));
            accountModel.setUsername(resultSet.getString("username"));
            accountModel.setPassword(resultSet.getString("password"));
            accountModel.setEmail(resultSet.getString("email"));
            accountModel.setRole(resultSet.getInt("role"));
            accountModel.setCreated_at(resultSet.getTimestamp("created_at"));
            accountModel.setUpdated_at(resultSet.getTimestamp("updated_at"));

            return accountModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
