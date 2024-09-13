package com.groundcollector.repository;

import com.groundcollector.model.Grounds;
import com.groundcollector.model.Teams;
import com.groundcollector.model.Users;
import com.groundcollector.service.UserService;
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
public class GroundsRepositoryImpl implements GroundsRepository {

    @Autowired
    public DataSource dataSource;

    @Autowired
    public UserService userService;

    Users users = new Users();

    String groundName = "";

    @Override
    public Grounds findIdByName(Teams teams, String str) {

        TeamsRepositoryImpl teamsRepositoryImpl = new TeamsRepositoryImpl();

        Grounds grounds = new Grounds();
        groundName = str;
        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> groundList : template.queryForList("SELECT id,name FROM grounds WHERE name = ?",
                groundName)) {

            grounds.setId((Integer) groundList.get("id"));
            grounds.setName((String) groundList.get("name"));

            teamsRepositoryImpl.updateGround(teams, (Integer) groundList.get("id"));

            System.out.println("Team: " + teams.getName());
            System.out.println("Ground ID: " + groundList.get("id"));
            System.out.println("Ground Name: " + groundList.get("name"));

        }

        return grounds;
    }

    @Override
    public Grounds findGroundsByName(String groundName) {

        Grounds ground = new Grounds();

        System.out.println("Is ground name set here already?: " + ground.getName());
        ground.setName(groundName);

        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (Map<String, Object> groundList : template.queryForList("SELECT id,capacity,built,image, FROM grounds WHERE name = ?",
                ground.getName())) {

            ground.setId((Integer) groundList.get("id"));
            ground.setCapacity((String) groundList.get("capacity"));
            ground.setBuilt((Integer) groundList.get("built"));
            ground.setImage((String) groundList.get("image"));

            System.out.println("Ground ID: " + groundList.get("id"));
            System.out.println("Ground Name: " + groundList.get("name"));

        }

        return ground;
    }

    @Override
    public List<Grounds> populateDropdown(List<Grounds> dropdown) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.query("SELECT g.id,g.name,t.league_id,t.name AS teamName FROM grounds g LEFT JOIN teams t ON t.ground_id = g.id ORDER BY " +
                "teamName, g.name ASC ", new RowMapper<List<Grounds>>() {

                    public List<Grounds> mapRow(ResultSet result, int rowNum) throws SQLException {

                        dropdown.add(new Grounds(result.getInt("id"), result.getString("name"), result.getString("teamName")));

                        return dropdown;

                    }

                }
        );
        return dropdown;
    }

    @Override
    public ArrayList<Grounds> geoLocationData(ArrayList<Grounds> geoLoc) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.query("SELECT g.id,g.name,g.lat,g.lng,g.capacity,g.city,t.name AS homeTeam, t.logo FROM grounds g LEFT JOIN teams t ON t.ground_id = g.id WHERE lat != '' AND lng != '';", new RowMapper<List<Grounds>>() {

                    public List<Grounds> mapRow(ResultSet result, int rowNum) throws SQLException {

                        // Below was used in testing, might be useful if we need a condensed version of stadium names
                        //String shortenedGroundName = (result.getString("name").replaceAll("Stadium", "").replaceAll("[-]+","").replaceAll("\\s+", ""));

                        geoLoc.add(new Grounds(result.getInt("id"), result.getString("name") , result.getString("lat"),result.getString("lng"),result.getString("capacity"),result.getString("city"),result.getString("homeTeam"),result.getString("logo")));
                        System.out.println("Team: " + result.getString("name") + " has image URL: " + result.getString("logo"));
                        return geoLoc;

                    }

                }
        );
        return geoLoc;
    }

    @Override
    public List<Grounds> geoLocationVisited(List<Grounds> geoLocVisited) {

        int currentUserId = userService.findUserId(users).getId();
        JdbcTemplate template = new JdbcTemplate(dataSource);

        String sql = String.format("SELECT g.id,g.name,g.lat,g.lng,g.capacity,g.city,t.name AS homeTeam, t.logo FROM grounds g LEFT JOIN teams t ON t.ground_id = g.id WHERE g.lat != '' AND g.lng != '' AND g.id IN (SELECT DISTINCT ground_id FROM entries WHERE user_id = %s)", currentUserId);

       template.query(sql, new RowMapper<List<Grounds>>() {

                    public List<Grounds> mapRow(ResultSet result, int rowNum) throws SQLException {

                        geoLocVisited.add(new Grounds(result.getInt("id"), result.getString("name") , result.getString("lat"),result.getString("lng"),result.getString("capacity"),result.getString("city"),result.getString("homeTeam"), result.getString("logo")));

                        return geoLocVisited;

                    }

                }
        );
        return geoLocVisited;
    }

    @Override
    public Grounds findGroundById(Grounds ground, Integer groundId) {

        JdbcTemplate template = new JdbcTemplate(dataSource);

        for (Map<String, Object> groundsList : template.queryForList("SELECT id,name FROM grounds WHERE id = ?",
                groundId)) {

            ground.setName((String) groundsList.get("name"));
            ground.setId((Integer) groundsList.get("ground_id"));

        }

        return ground;
    }

    @Override
    public Grounds findGroundDetailsByName(Grounds ground, String groundName, JdbcTemplate template) {

        for (Map<String, Object> individualGround : template.queryForList("SELECT id, name,capacity, image, built, active, lat, lng FROM grounds WHERE name = ?",
                groundName)) {

            ground.setId((Integer) individualGround.get("id"));
            ground.setName((String) individualGround.get("name"));
            ground.setImage((String) individualGround.get("image"));
            ground.setCapacity((String) individualGround.get("capacity"));
            ground.setBuilt((Integer) individualGround.get("built"));
            ground.setActive((Boolean) individualGround.get("active"));
            ground.setLng((String) individualGround.get("lng"));
            ground.setLat((String) individualGround.get("lat"));

        }

        return ground;
    }

    @Override
    public boolean existsByName(String name) {

        return false;
    }

    @Override
    public Grounds updateGroundDetails(Grounds ground, Integer groundId, JdbcTemplate template) {

        template.update("UPDATE grounds SET capacity = ?, image = ? WHERE id = ? ",
                ground.getCapacity(),
                ground.getImage(),
                groundId);

        return ground;
    }

    @Override
    public Boolean groundDetailsAllMatch(Grounds ground, String groundName, JdbcTemplate template) {

        Integer groundId = 0;

        System.out.println("Check ground details before querying against them: " + ground.toString());

        for (Map<String, Object> groundList : template.queryForList("SELECT id FROM grounds " +
                        "WHERE capacity = ? AND city = ?",
                ground.getCapacity(),
                ground.getCity())) {

            ground.setId((Integer) groundList.get("id"));

        }

        groundId = ground.getId();
        System.out.println("Checking ground ID before boolean check: " + groundId);
        if(!groundId.equals(0)){
            return true;
        }

        return false;
    }

    @Override
    public Grounds findGroundNameByDetails(Grounds ground, JdbcTemplate template) {

        System.out.println("ground details just before executing query: " + ground.getCapacity() + ", " + ground.getCity() + ", " + ground.getLat() + ", " + ground.getLng());

        String sql;

        // TODO this first IF will always be true currently as there's no way to pull the lat and lng from the db, meaning they're always null
        if (ground.getLat() == null || ground.getLng() == null){

            sql = "SELECT id, name, aliases, lat, lng  FROM grounds WHERE capacity = ? AND city = ?";

            System.out.println("Searching using capacity and city");

            for (Map<String, Object> groundList : template.queryForList(sql, ground.getCapacity(), ground.getCity())) {

                ground.setId((Integer) groundList.get("id"));
                ground.setName((String) groundList.get("name"));
                ground.setAliases((String) groundList.get("aliases"));
                ground.setLat((String) groundList.get("lat"));
                ground.setLng((String) groundList.get("lng"));

            }
        }
        else {
            sql = "SELECT id, name, aliases  FROM grounds WHERE lat = ? AND lng = ?";

            System.out.println("Searching using lat and lng");

            for (Map<String, Object> groundList : template.queryForList(sql, ground.getLat(), ground.getLng())) {

                ground.setId((Integer) groundList.get("id"));
                ground.setName((String) groundList.get("name"));
                ground.setAliases((String) groundList.get("aliases"));
            }
        }
        System.out.println("ground details after executing query: " + ground.getName() + ", " + ground.getId());

        return ground;
    }

    @Override
    public Grounds updateGroundName(Grounds ground, String newGroundName, JdbcTemplate template) {

        if (ground.getAliases() == null) {
        /*template.update("UPDATE grounds SET name = ?, aliases = ? WHERE id = ? ",
                newGroundName,
                ground.getName(),
                ground.getId());
         */

            System.out.println("IF statement is true - update here would be new Name: " + newGroundName + ", new Aliases: " + ground.getName());

        } else {
             /*template.update("UPDATE grounds SET name = ?, aliases = ? WHERE id = ? ",
                newGroundName,
                ground.getName() + "," + ground.getAliases(),
                ground.getId());
         */

            System.out.println("ELSE - update here would be new Name: " + newGroundName + ", new Aliases: " + ground.getName() + "," + ground.getAliases());

        }

        System.out.println("update here would be new Name: " + newGroundName + ", new Aliases: " + ground.getName() + "," + ground.getAliases());
        return ground;
    }


    @Override
    public List<Grounds> populateChecklist(List<Grounds> checklist, List<Grounds> visitedChecklist) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.query("SELECT g.id,g.name,t.league_id,t.name AS teamName FROM grounds g LEFT JOIN teams t ON t.ground_id = g.id ORDER BY " +
                        "teamName, g.name ASC ", new RowMapper<List<Grounds>>() {

                    public List<Grounds> mapRow(ResultSet result, int rowNum) throws SQLException {

                        for (Grounds ground : visitedChecklist) {

                            if (ground.getName().contains(result.getString("name"))) {

                                return checklist;
                            }
                        }

                        checklist.add(new Grounds(result.getInt("id"), result.getString("name"), result.getString("teamName")));

                        return checklist;
                    }
                });
        return checklist;
    }

    @Override
    public List<Grounds> populateVisitedChecklist(List<Grounds> visitedChecklist) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        int currentUserId = userService.findUserId(users).getId();

        String sql = "SELECT DISTINCT g.name, g.id, t.name AS teamName " +
                "FROM entries e " +
                "LEFT JOIN grounds g " +
                "ON g.id = e.ground_id " +
                "LEFT JOIN teams t " +
                "ON g.id = t.ground_id " +
                "WHERE e.user_id = ? " +
                "GROUP BY g.name, g.id, teamName " +
                "ORDER BY g.name ASC ;";


        for (Map<String, Object> groundList : template.queryForList(sql,currentUserId)) {

            Grounds visitedGround = new Grounds();
            visitedGround.setId((Integer) groundList.get("id"));
            visitedGround.setName((String) groundList.get("name"));
            visitedGround.setHomeTeam((String) groundList.get("teamName"));
            visitedChecklist.add(visitedGround);

        }

        return visitedChecklist;
    }
    // TODO Logic for old / demolished grounds to be shown in the map
}


