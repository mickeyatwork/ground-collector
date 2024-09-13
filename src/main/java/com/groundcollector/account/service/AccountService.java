package com.groundcollector.account.service;

import com.groundcollector.account.model.Account;

public interface AccountService {

    public Account create (Account account);

    void createVerificationToken (Account account, String token);

    void confirmAccount(String token);
}
