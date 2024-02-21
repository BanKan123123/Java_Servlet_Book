package com.example.dao;

import com.example.model.AccountModel;

import java.util.List;

public interface IAccount {

    List<AccountModel> findAllAccount();

    AccountModel findOneAccount();

    void signInAccount(AccountModel account);

    void registerAccount (AccountModel account);


}
