package com.groundcollector.repository;

import com.groundcollector.model.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CompetitionRepositoryImpl implements CompetitionRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Competition> populateDropdown(List<Competition> dropdown) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.query("SELECT id,name,country,tier,type FROM competition ORDER BY country,tier ASC ", new RowMapper<List<Competition>>() {

                    public List<Competition> mapRow(ResultSet result, int rowNum) throws SQLException {

                        dropdown.add(new Competition(result.getInt("id"), result.getString("name"), result.getString("country"), result.getInt("tier"), result.getString("type")));

                        return dropdown;
                    }
                }
        );
        return dropdown;
    }

    @Override
    public List<Competition> populateDropdownLeaguesOnly(List<Competition> leagueDropdown) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.query("SELECT id,name,country,tier,api_id FROM competition WHERE type = 'League' ORDER BY country,tier ASC ", new RowMapper<List<Competition>>() {

                    public List<Competition> mapRow(ResultSet result, int rowNum) throws SQLException {

                        leagueDropdown.add(new Competition(result.getInt("id"), result.getString("name"), result.getString("country"), result.getInt("tier"), result.getInt("api_id")));

                        return leagueDropdown;
                    }
                }
        );
        return leagueDropdown;
    }
}
