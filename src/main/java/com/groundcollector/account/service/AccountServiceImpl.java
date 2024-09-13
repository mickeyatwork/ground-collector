package com.groundcollector.account.service;

import com.groundcollector.account.model.Account;
import com.groundcollector.account.model.VerificationToken;
import com.groundcollector.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        return accountRepository.create(account);
    }

    @Override
    public void createVerificationToken(Account account, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsername(account.getUsername());

        accountRepository.saveToken(verificationToken);

    }

    @Override
    public void confirmAccount(String token) {
        //retrieve token
        VerificationToken verificationToken = accountRepository.findByToken(token);
        //verify date
        if (verificationToken.getExpiryDate().after(new Date())) {
            //move from account table to userDetails table
            Account account = accountRepository.findByUsername(verificationToken.getUsername());
            // create user detailS
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
/*
            UserDetails userDetails =
                    new ConferenceUserDetails(account.getUsername(),
                            account.getPassword(),
                            authorities);
            accountRepository.createUserDetails(userDetails);
            accountRepository.createAuthorities(userDetails);
            //delete from accounts
            accountRepository.delete(account);
            // deleted from tokens
            accountRepository.deleteToken(token);

 */
        }

    }

}