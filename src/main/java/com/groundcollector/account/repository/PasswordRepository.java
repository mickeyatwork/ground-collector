package com.groundcollector.account.repository;

import com.groundcollector.account.model.Password;
import com.groundcollector.account.model.ResetToken;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository {

    void saveToken(ResetToken resetToken);

    ResetToken findByToken(String token);

    void update(Password password, String username);

}
