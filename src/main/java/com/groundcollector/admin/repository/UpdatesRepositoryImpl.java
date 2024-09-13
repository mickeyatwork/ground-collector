package com.groundcollector.admin.repository;

import com.groundcollector.model.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class UpdatesRepositoryImpl implements UpdatesRepository {

    @Autowired
    public DataSource dataSource;

    @Override
    public Teams updateTeam(Teams team) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE teams SET founded = ?, country = ?, league_id = ?, ground_id = ?" +
                        "WHERE name = ?",
                team.getFounded(),
                team.getCountry(),
                team.getLeagueId(),
                team.getGroundId(),
                team.getName());

        return team;
    }

    @Override
    public Teams mapGround(Teams team, String groundName) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> groundList : template.queryForList("SELECT id,name FROM grounds WHERE name = ?",
                groundName)) {

            team.setGroundId((Integer) groundList.get("id"));

            System.out.println("Updating " + team.getName() + " to have ground ID of: " + team.getGroundId());
            template.update("UPDATE teams SET ground_id = ? " +
                            "WHERE name = ?",
                    team.getGroundId(),
                    team.getName());

            return team;
        }
        return team;
    }
}
