package com.groundcollector.repository;

import com.groundcollector.model.Entry;
import com.groundcollector.model.TravelLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class EntryRepositoryImpl implements EntryRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public Entry create(Entry entry) {

        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("INSERT INTO entries (user_id, home_team_id, away_team_id, ground_id, date_visited, notes, created, competition_id, score) VALUES " +
                        "(?,?,?,?,?,?,now(),?,?)",
                entry.getUserId(),
                entry.getHomeTeamId(),
                entry.getAwayTeamId(),
                entry.getGroundId(),
                entry.getDateVisited(),
                entry.getNotes(),
                entry.getCompetitionId(),
                entry.getScore());

        return entry;
    }

    @Override
    public Entry edit(Entry entry) {

        System.out.println("Just before update: " + entry.getId() + "\n" + entry.getUserId() + "\n" + entry.getNotes());
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE entries SET home_team_id = ?, away_team_id = ?, ground_id = ?, date_visited = ?, notes = ?, last_edited = now(), competition_id = ?, score = ? " +
                        "WHERE id = ?",
                    entry.getHomeTeamId(),
                    entry.getAwayTeamId(),
                    entry.getGroundId(),
                    entry.getDateVisited(),
                    entry.getNotes(),
                    entry.getCompetitionId(),
                    entry.getScore(),
                    entry.getId());

            return entry;
        }

    @Override
    public TravelLog findLogById(Entry entry, TravelLog travelLog) {

        String sql = "SELECT * FROM (" +
                "WITH hometeam AS " +
                "( SELECT  e.id, e.user_id, t.name, t.logo " +
                "FROM entries e  " +
                "LEFT JOIN teams t ON t.id = e.home_team_id " +
                "WHERE e.id = ? ), " +
                "awayteam AS " +
                "( SELECT  e.id, e.user_id, t.name, t.logo " +
                "FROM entries e  " +
                "LEFT JOIN teams t ON t.id = e.away_team_id " +
                "WHERE e.id = ? ) " +
                "SELECT  e.id, e.user_id, h.name AS home_team, a.name AS away_team, e.score, g.name AS ground, e.date_visited AS date, to_char(e.date_visited, 'yyyy-MM-dd') AS date_visited, e.notes, c.name AS competition " +
                "FROM entries e  " +
                "LEFT JOIN hometeam h  ON h.id = e.id " +
                "LEFT JOIN awayteam a  ON a.id = e.id " +
                "LEFT JOIN grounds g ON g.id = e.ground_id " +
                "LEFT JOIN competition c ON c.id = e.competition_id " +
                "WHERE e.id = ? ORDER BY date,e.id DESC) as full_travel_log";

        int currentId = entry.getId();
        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> editingEntry : template.queryForList(sql, currentId, currentId, currentId)) {

            travelLog.setId((Integer) editingEntry.get("id"));
            travelLog.setUserId((Integer) editingEntry.get("user_id"));
            travelLog.setHomeTeam((String) editingEntry.get("home_team"));
            travelLog.setAwayTeam((String) editingEntry.get("away_team"));
            travelLog.setScore((String) editingEntry.get("score"));
            travelLog.setGround((String) editingEntry.get("ground"));
            travelLog.setDateVisited((String) editingEntry.get("date_visited"));
            travelLog.setNotes((String) editingEntry.get("notes"));
            travelLog.setCompetition((String) editingEntry.get("competition"));
            travelLog.setHomeBadge((String) editingEntry.get("homeBadge"));
            travelLog.setAwayBadge((String) editingEntry.get("awayBadge"));
            System.out.println("travelLog: " + travelLog);

            return travelLog;
        }

        return travelLog;
    }

    @Override
    public Entry findEntryById(Entry entry) {
        String sql = "SELECT  id, user_id, score, competition_id, away_team_id, home_team_id, ground_id " +
                "FROM entries " +
                "WHERE id = ?";

        int currentId = entry.getId();
        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> editingEntry : template.queryForList(sql, currentId)) {

            entry.setHomeTeamId((Integer) editingEntry.get("home_team_id"));
            entry.setAwayTeamId((Integer) editingEntry.get("away_team_id"));
            entry.setCompetitionId((Integer) editingEntry.get("competition_id"));
            entry.setGroundId((Integer) editingEntry.get("ground_id"));

            return entry;
        }

        return entry;
    }

    @Override
    public Entry delete(Entry entry) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("DELETE FROM entries WHERE id = ?",
                entry.getId());

        return entry;
    }
}
