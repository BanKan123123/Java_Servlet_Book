package com.example.dao;

import com.example.model.AccountModel;

import java.util.List;

public interface IAccountDAO {

    List<AccountModel> findAllAccount();

    AccountModel findOneAccount(String username);

    List<AccountModel> findAllUserAccount ();

    AccountModel findOneById(String id);

    AccountModel signInAccount(String username, String password);

    void registerAccount(AccountModel account);


}
