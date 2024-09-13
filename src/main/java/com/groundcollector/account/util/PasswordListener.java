package com.groundcollector.account.util;

import com.groundcollector.account.model.Password;
import com.groundcollector.account.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PasswordListener implements ApplicationListener<OnPasswordResetEvent> {

    /* TODO Will need changing if we go live with emails */
    private String serverUrl = "http://localhost:8080/";

    //@Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordService passwordService;

    @Override
    public void onApplicationEvent(OnPasswordResetEvent event) {
        this.resetPassword(event);
    }

    private void resetPassword(OnPasswordResetEvent event) {
        //create password token
        Password password = event.getPassword();
        String token = UUID.randomUUID().toString();
        passwordService.createResetToken(password, token);

        //get email properties
        String recipientAddress = password.getEmail();
        String subject = "Reset Password";
        String confirmationUrl = event.getAppUrl() + "/passwordReset?token=" + token;
        String message = "Reset Password:";

        //send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + serverUrl + confirmationUrl);
        //mailSender.send(email);

    }
}
