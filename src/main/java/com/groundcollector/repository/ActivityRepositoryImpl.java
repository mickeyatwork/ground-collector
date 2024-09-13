package com.groundcollector.repository;

import com.groundcollector.model.Menu;
import com.groundcollector.model.Users;
import com.groundcollector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class ActivityRepositoryImpl implements ActivityRepository {

    @Autowired
    public DataSource dataSource;

    @Autowired
    public UserService userService;

    Users users = new Users();

    @Override
    public Menu findLogCount(Menu menu) {

        int currentUserId = userService.findUserId(users).getId();

        String sql = "SELECT COUNT(*) AS count FROM entries WHERE user_id = ?";

        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> entryList : template.queryForList(sql,currentUserId)) {

            menu.setLogCount((Long) entryList.get("count"));

        }
        System.out.println("Log Count: " + menu.getLogCount());
        return menu;
    }

    @Override
    public Menu findUniqueGroundsVisited(Menu menu) {

        int currentUserId = userService.findUserId(users).getId();

        String sql = "SELECT COUNT(*) FROM grounds WHERE id IN (SELECT DISTINCT ground_id FROM entries WHERE user_id = ?)";

        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> entryList : template.queryForList(sql,currentUserId)) {

            menu.setUniqueVisitCount((Long) entryList.get("count"));

        }
        System.out.println("Unique visit total: " + menu.getUniqueVisitCount());
        return menu;
    }

}
