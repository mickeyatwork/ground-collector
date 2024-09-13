package com.groundcollector.repository;

import com.groundcollector.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public Users findUserId(Users users) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object loggedInUser = authentication.getName().toString();

        JdbcTemplate template = new JdbcTemplate(dataSource);

        String sql = "SELECT * FROM users";
        template.query(sql, new RowMapper<Users>() {

            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

                {

                    if (rs.getString("username").equals(loggedInUser.toString())) {
                        users.setId(rs.getInt("id"));
                        users.setUsername(rs.getString("username"));
                        return users;
                    }
                }

                return users;
            }
        });
        return users;
    }
}



