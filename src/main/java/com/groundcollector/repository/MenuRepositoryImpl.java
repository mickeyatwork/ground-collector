package com.groundcollector.repository;

import com.groundcollector.model.TravelLog;
import com.groundcollector.model.Users;
import com.groundcollector.model.Menu;

import com.groundcollector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    private static Logger logger = LogManager.getLogger(MenuRepositoryImpl.class);

    @Autowired
    public DataSource dataSource;

    @Autowired
    public UserService userService;

    Users users = new Users();

    @Override
    public TravelLog findLatestEntry(TravelLog travelLog) {

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
                "WHERE e.user_id = ? ORDER BY date DESC) as full_travel_log lIMIT 1";

        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> entryList : template.queryForList(sql,currentUserId,currentUserId,currentUserId )) {

            travelLog.setId((Integer) entryList.get("id"));
            travelLog.setUserId((Integer) entryList.get("user_id"));
            travelLog.setHomeTeam((String) entryList.get("home_team"));
            travelLog.setAwayTeam((String) entryList.get("away_team"));
            travelLog.setHomeBadge((String) entryList.get("home_logo"));
            travelLog.setAwayBadge((String) entryList.get("away_logo"));
            travelLog.setScore((String) entryList.get("score"));
            travelLog.setGround((String) entryList.get("ground"));
            travelLog.setDateVisited((String) entryList.get("date_visited"));
            travelLog.setNotes((String) entryList.get("notes"));
            travelLog.setCompetition((String) entryList.get("competition"));

        }
        logger.info("travelLog: " + travelLog);
        System.out.println("travelLog: " + travelLog);
        return travelLog;
    }

    @Override
    public Menu findLogCount(Menu menu) {

        int currentUserId = userService.findUserId(users).getId();

        String sql = "SELECT COUNT(*) AS count FROM entries WHERE user_id = ?";

        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> entryList : template.queryForList(sql,currentUserId)) {

            menu.setLogCount((Long) entryList.get("count"));
        }
        return menu;
    }

    @Override
    public ArrayList<Menu> findMostVisited(ArrayList<Menu> menu) {

        int currentUserId = userService.findUserId(users).getId();

        String sql = "SELECT ground_name, visit_count\n" +
                "FROM (\n" +
                "   SELECT ground_name, visit_count,\n" +
                "          RANK() OVER (\n" +
                "              ORDER BY visit_count DESC) AS rank\n" +
                "   FROM (\n" +
                "      SELECT g.name AS ground_name, COUNT(e.ground_id) AS visit_count FROM entries e LEFT JOIN grounds g ON g.id = e.ground_id WHERE e.user_id = ? \n" +
                "      GROUP BY ground_name ORDER BY visit_count DESC ) ranking\n" +
                ") r\n" +
                "WHERE r.rank = 1\n" +
                "ORDER BY rank DESC";

        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> entryList : template.queryForList(sql, currentUserId)) {

            Menu mostVisited = new Menu();
            mostVisited.setVisitCount(Math.toIntExact((Long) entryList.get("visit_count")));
            mostVisited.setVisitedGround((String) entryList.get("ground_name"));
            menu.add(mostVisited);

            return menu;
        }
        return menu;
    }
}
