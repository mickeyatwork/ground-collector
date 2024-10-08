package com.groundcollector.account.util;

import com.groundcollector.account.model.Password;
import org.springframework.context.ApplicationEvent;

import javax.validation.Valid;

public class OnPasswordResetEvent extends ApplicationEvent {

    private String appUrl;
    private Password password;

    public OnPasswordResetEvent(@Valid Password password, String appUrl) {
        super(password);

        this.appUrl = appUrl;
        this.password = password;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}
