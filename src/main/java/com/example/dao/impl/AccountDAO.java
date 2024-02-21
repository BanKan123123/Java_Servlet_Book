package com.example.dao.impl;

import com.example.dao.IAccount;
import com.example.model.AccountModel;
import com.example.model.BookModel;

import java.util.List;

public class AccountDAO extends AbstractDAO<AccountModel> implements IAccount {
    @Override
    public List<AccountModel> findAllAccount() {
        return null;
    }

    @Override
    public AccountModel findOneAccount() {
        return null;
    }

    @Override
    public void signInAccount(AccountModel account) {

    }

    @Override
    public void registerAccount(AccountModel account) {

    }
}
