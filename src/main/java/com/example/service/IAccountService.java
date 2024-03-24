package com.example.service;

import com.example.model.AccountModel;

import java.util.List;

public interface IAccountService {

    List<AccountModel> findAllAccount();

    AccountModel findOneAccount(String username);

    List<AccountModel> findAllUserAccount();

    AccountModel login(String username, String password);

    AccountModel save(AccountModel accountModel);

}
