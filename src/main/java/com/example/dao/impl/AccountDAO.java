package com.example.dao.impl;

import com.example.dao.IAccount;
import com.example.mapper.AccountMapper;
import com.example.model.AccountModel;

import java.util.List;

public class AccountDAO extends AbstractDAO<AccountModel> implements IAccount {
    @Override
    public List<AccountModel> findAllAccount() {
        String sql = "SELECT * FROM account ORDER BY username ASC";
        return query(sql, new AccountMapper());
    }

    @Override
    public AccountModel findOneAccount(String username) {
        String sql = "SELECT * FROM account WHERE `username` = ?";

        if (!query(sql, new AccountMapper(), username).isEmpty()) {
            return query(sql, new AccountMapper(), username).get(0);
        } else {
            return null;
        }
    }

    @Override
    public AccountModel signInAccount(String username, String password) {
        String sql = "SELECT * FROM account WHERE `username` = ? AND `password` = ?";
        if (!query(sql, new AccountMapper(), username, password).isEmpty()) {
            return query(sql, new AccountMapper(), username, password).get(0);
        } else {
            return null;
        }
    }

    @Override
    public void registerAccount(AccountModel account) {
        String sql = "INSERT INTO account (username, password, email, phoneNumber, role) VALUE (? , ? , ?, ?, 0)";
        insert(sql, account.getUsername(), account.getPassword(), account.getEmail(), account.getPhoneNumber());
    }
}
