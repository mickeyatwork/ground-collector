package com.groundcollector.repository;

import com.groundcollector.model.TravelLog;
import com.groundcollector.model.Users;
import com.groundcollector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TravelLogRepositoryImpl implements TravelLogRepository {

    @Autowired
    public DataSource dataSource;

    @Autowired
    public UserService userService;

    Users users = new Users();

    @Override
    public ArrayList<TravelLog> findAllEntriesByUserID(ArrayList<TravelLog> travelLog) {

        int currentUserId = userService.findUserId(users).getId();

        String sql = "SELECT * FROM (" +
                "WITH hometeam AS " +
                "( SELECT  e.id, e.user_id, t.name, t.logo " +
                "FROM entries e  " +
                "LEFT JOIN teams t ON t.id = e.home_team_id " +
                "WHERE e.user_id = ? ), " +
                "awayteam AS " +
                "( SELECT  e.id, e.user_id, t.name, t.logo " +
                "FROM entries e  " +
                "LEFT JOIN teams t ON t.id = e.away_team_id " +
                "WHERE e.user_id = ? ) " +
                "SELECT  e.id, e.user_id, h.name AS home_team, h.logo AS home_logo,  a.name AS away_team, a.logo AS away_logo, e.score, g.name AS ground,e.date_visited AS date, to_char(e.date_visited, 'dd/MM/yyyy') AS date_visited, e.notes, c.name AS competition " +
                "FROM entries e  " +
                "LEFT JOIN hometeam h  ON h.id = e.id " +
                "LEFT JOIN awayteam a  ON a.id = e.id " +
                "LEFT JOIN grounds g ON g.id = e.ground_id " +
                "LEFT JOIN competition c ON c.id = e.competition_id " +
                "WHERE e.user_id = ? ORDER BY date DESC) as full_travel_log";

        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> entryList : template.queryForList(sql,currentUserId,currentUserId,currentUserId )) {

            TravelLog tLog = new TravelLog();
            tLog.setId((Integer) entryList.get("id"));
            tLog.setUserId((Integer) entryList.get("user_id"));
            tLog.setHomeTeam((String) entryList.get("home_team"));
            tLog.setAwayTeam((String) entryList.get("away_team"));
            tLog.setHomeBadge((String) entryList.get("home_logo"));
            tLog.setAwayBadge((String) entryList.get("away_logo"));
            tLog.setScore((String) entryList.get("score"));
            tLog.setGround((String) entryList.get("ground"));
            tLog.setDateVisited((String) entryList.get("date_visited"));
            tLog.setNotes((String) entryList.get("notes"));
            tLog.setCompetition((String) entryList.get("competition"));
            travelLog.add(tLog);

        }
        System.out.println("travelLog: " + travelLog);
        return travelLog;
    }
}
