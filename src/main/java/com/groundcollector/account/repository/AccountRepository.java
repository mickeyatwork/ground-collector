package com.groundcollector.account.repository;

import com.groundcollector.account.model.Account;
import com.groundcollector.account.model.UserDetails;
import com.groundcollector.account.model.VerificationToken;

public interface AccountRepository {

    public Account create (Account account);

    void saveToken(VerificationToken verificationToken);

    VerificationToken findByToken(String token);

    Account findByUsername(String username);

    // void createUserDetails(UserDetails userDetails);

    void createAuthorities(UserDetails userDetails);
    // TODO When email verification goes live, this can be added back in
    /*
        @Override
        public void createUserDetails(UserDetails userDetails) {
            JdbcTemplate template = new JdbcTemplate(dataSource);
            template.update("INSERT INTO users (username, password, enabled) VALUES " +
                            "(?,?,?)",
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    1);
        }

     */

    void delete(Account account);

    void deleteToken(String token);
}
