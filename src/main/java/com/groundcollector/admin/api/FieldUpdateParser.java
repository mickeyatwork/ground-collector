package com.groundcollector.admin.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.groundcollector.admin.repository.TeamRepository;
import com.groundcollector.model.Teams;
import com.groundcollector.repository.TeamsRepositoryImpl;
import com.groundcollector.utils.ApiConfig;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class FieldUpdateParser {
    JsonFactory jsonFactory = new JsonFactory();
    JsonParser jsonParser;
    Teams teams = new Teams();
    HashMap<String, Object> map = new HashMap<String, Object>();



    public int leagueIdForApi;

    public String updateFieldName;

    public int getLeagueIdForApi() {
        return leagueIdForApi;
    }

    public void setLeagueIdForApi(int leagueIdForApi) {
        this.leagueIdForApi = leagueIdForApi;
    }

    public String getUpdateFieldName() {
        return updateFieldName;
    }

    public void setUpdateFieldName(String updateFieldName) {
        this.updateFieldName = updateFieldName;
    }



    public void insertTeam(TeamRepository repository, JdbcTemplate template, ApiConfig apiConfig) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api-football-v1.p.rapidapi.com/v3/teams?league=" + leagueIdForApi + "&season=2024"))
                .header(apiConfig.getApiKeyHeader(), apiConfig.getApiKey())
                .header(apiConfig.getApiHostHeader(), apiConfig.getApiHost())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        {

            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("API call: " + response);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        {
            try {
                jsonParser = jsonFactory.createParser(response.body());

                while (jsonParser.nextToken() != null) {

                    JsonToken token = jsonParser.getCurrentToken();
                    //System.out.println(token);

                    if (token.equals(JsonToken.FIELD_NAME)) {
                        String fieldName = jsonParser.getCurrentName();
                        jsonParser.nextToken();
                        if (fieldName.equals("team")) {

                            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                                String nameField = jsonParser.getCurrentName();
                                jsonParser.nextToken();
                                if (nameField.equals("id")) {
                                    teams.setId(jsonParser.getIntValue());
                                } else if (nameField.equals("name")) {
                                    teams.setName(jsonParser.getValueAsString());
                                    map.put("name", teams.getName());

                                } else if (nameField.equals("country")) {
                                    teams.setCountry(jsonParser.getValueAsString());
                                    map.put("country", teams.getCountry());

                                } else if (nameField.equals("founded")) {
                                    teams.setFounded(jsonParser.getValueAsInt());
                                    map.put("founded", (teams.getFounded()));

                                } else if (nameField.equals("logo")) {
                                    teams.setLogo(jsonParser.getValueAsString());
                                    map.put("logo", teams.getLogo());

                                }

                            }

                            System.out.println("Team mapping: " + map);

                            /* ADDING_LEAGUES - add any new leagues here*/
                            //TODO Can we make this a dynamic thing, like search in the db and update / add the league ID based off the API ID stored?

                            if (repository.existsByName(String.valueOf(map.get("name")))) {
                                // TODO create method to only pull one team by ID here and add single methods for each field we're updating
                                TeamsRepositoryImpl teamsRepository = new TeamsRepositoryImpl();
                                Teams updateTeam = new Teams(String.valueOf(map.get("name")));
                                teamsRepository.findTeamIdByName2(updateTeam, updateTeam.getName(), template);

                            if (updateFieldName.equals("name")) {
                                updateTeam.setName((String) map.get("name"));
                                repository.save(updateTeam);

                            } else if (updateFieldName.equals("league")) {
                                if (leagueIdForApi == 39) {
                                    updateTeam.setLeagueId(1);
                                    repository.save(updateTeam);

                                } else if (leagueIdForApi == 40) {
                                    updateTeam.setLeagueId(2);
                                    repository.save(updateTeam);

                                } else if (leagueIdForApi == 41) {
                                    updateTeam.setLeagueId(3);
                                    repository.save(updateTeam);

                                } else if (leagueIdForApi == 42) {
                                    updateTeam.setLeagueId(4);
                                    repository.save(updateTeam);

                                } else if (leagueIdForApi == 43) {
                                    updateTeam.setLeagueId(8);
                                    repository.save(updateTeam);

                                } else {
                                    updateTeam.setLeagueId(0);
                                    repository.save(updateTeam);
                                }

                            } else if (updateFieldName.equals("logo")) {
                                updateTeam.setLogo((String) map.get("logo"));
                                repository.save(updateTeam);


                            } else {
                            }
                        }
                    }
                }
            }
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("-------------------------------------");
        }
    }
    /* TODO Update leagues for promoted / deleted teams (Kind of works in 'IndividualTeamUpdate page' but you need to add the ground in as well which could be a pain */
};

