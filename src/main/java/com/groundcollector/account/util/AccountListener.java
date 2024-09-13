package com.groundcollector.account.util;

import com.groundcollector.account.model.Account;
import com.groundcollector.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountListener implements ApplicationListener<OnCreateAccountEvent> {

    /* TODO Will need changing if we go live with emails */
    private String serverUrl = "http://local:8080/";

    //@Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AccountService accountService;

    @Override
    public void onApplicationEvent(OnCreateAccountEvent event) {
        this.confirmCreateAccount(event);
    }

    private void confirmCreateAccount(OnCreateAccountEvent event) {
        //Get the account
        //Create verification token
        Account account = event.getAccount();
        String token = UUID.randomUUID().toString();
        accountService.createVerificationToken(account, token);
        //Get email properties
        String recipientAddress = account.getEmail();
        String subject = "Account Confirmation";
        String confirmationUrl = event.getAppUrl() + "/accountConfirm?token=" + token;
        String message = "Please confirm:";
        //Send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + serverUrl + confirmationUrl);
        //mailSender.send(email);
    }
}
