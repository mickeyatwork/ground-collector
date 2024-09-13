package com.groundcollector.account.service;

import com.groundcollector.account.model.Password;
import com.groundcollector.account.model.ResetToken;

public interface PasswordService {

    void createResetToken (Password password, String token);

    boolean confirmResetToken (ResetToken token);

    void update(Password password, String username);
}
