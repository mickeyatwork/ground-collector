package com.groundcollector.repository;

import com.groundcollector.model.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TeamsRepositoryImpl implements TeamsRepository {

    @Autowired
    public DataSource dataSource;

    List<Teams> listOfTeams = new ArrayList<>();

    @Override
    public Teams findAll(Teams teams) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.query("SELECT id,name FROM teams ", new RowMapper<Teams>() {

            public Teams mapRow(ResultSet result, int rowNum) throws SQLException {

                teams.setId(result.getInt("id"));
                teams.setName(result.getString("name"));
                listOfTeams.add(teams);
               System.out.println("Team ID: " + teams.getId());
               System.out.println("Team Name: " + teams.getName());

                return teams;
            }
        });
        return teams;
    }

    @Override
    public Teams updateGround(Teams teams, Integer integer) {

        System.out.println("Team: " + teams.getName() + "\nGround ID: " + integer);
        System.out.println("About to create a new dateSource");
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE teams SET ground_id = ? WHERE id = ? ",
                integer,
                teams.getId());

        return teams;
    }

    @Override
    public List<Teams> populateDropdown(List<Teams> dropdown) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.query("SELECT id,name,logo,league_id FROM teams ORDER BY name ASC ", new RowMapper<List<Teams>>() {

                    public List<Teams> mapRow(ResultSet result, int rowNum) throws SQLException {

                        dropdown.add(new Teams(result.getInt("id"), result.getString("name"), result.getString("logo"), result.getInt("league_id")));

                        return dropdown;
                    }
                }
        );
        return dropdown;
    }

    @Override
    public Teams findTeamById(Teams team, Integer teamId) {

        JdbcTemplate template = new JdbcTemplate(dataSource);

        for (Map<String, Object> teamsList : template.queryForList("SELECT id,name,ground_id,league_id,logo FROM teams WHERE id = ?",
                teamId)) {

            team.setName((String) teamsList.get("name"));
            team.setGroundId((Integer) teamsList.get("ground_id"));
            team.setLeagueId((Integer) teamsList.get("league_id"));
            team.setLogo((String) teamsList.get("logo"));

        }
        return team;
    }

    @Override
    public Teams findTeamIdByName(Teams team, String teamName) {

        JdbcTemplate template = new JdbcTemplate(dataSource);

        TeamsRepositoryImpl teamsRepositoryImpl = new TeamsRepositoryImpl();
        for (Map<String, Object> individualTeam : template.queryForList("SELECT id,name,ground_id,league_id,logo FROM teams WHERE id = ?",
                teamName)) {

            team.setId((Integer) individualTeam.get("id"));
            team.setName((String) individualTeam.get("name"));

            teamsRepositoryImpl.updateGround(team, (Integer) individualTeam.get("id"));

            System.out.println("Team: " + team.getName() + ",ID: " + team.getId());
        }
        return team;
    }

    @Override
    public Teams findTeamIdByName2(Teams team, String teamName, JdbcTemplate template) {

        for (Map<String, Object> individualTeam : template.queryForList("SELECT id,name,ground_id,league_id,logo,country,founded,nation FROM teams WHERE name = ?",
                teamName)) {

            team.setId((Integer) individualTeam.get("id"));
            team.setName((String) individualTeam.get("name"));
            team.setLeagueId((Integer) individualTeam.get("league_id"));
            team.setGroundId((Integer) individualTeam.get("ground_id"));
            team.setCountry((String) individualTeam.get("country"));
            team.setFounded((Integer) individualTeam.get("founded"));
            team.setNation((Boolean) individualTeam.get("nation"));
            team.setLogo((String) individualTeam.get("logo"));

        }
        return team;
    }

    @Override
    public Teams findTeamById2(Teams team, Integer teamId, JdbcTemplate template) {

        for (Map<String, Object> teamsList : template.queryForList("SELECT id,name,ground_id,league_id,logo FROM teams WHERE id = ?",
                teamId)) {

            team.setName((String) teamsList.get("name"));
            team.setGroundId((Integer) teamsList.get("ground_id"));
            team.setLeagueId((Integer) teamsList.get("league_id"));
            team.setLogo((String) teamsList.get("logo"));

        }
        return team;
    }
}
