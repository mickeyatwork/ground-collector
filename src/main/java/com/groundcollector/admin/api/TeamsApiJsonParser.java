package com.groundcollector.admin.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.groundcollector.admin.repository.TeamRepository;
import com.groundcollector.model.Teams;
import com.groundcollector.utils.ApiConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import static java.lang.Boolean.FALSE;

public class TeamsApiJsonParser {
    JsonFactory jsonFactory = new JsonFactory();
    JsonParser jsonParser;
    Teams teams = new Teams();
    HashMap<String, Object> map = new HashMap<String, Object>();

    public int leagueIdForApi;

    public int getLeagueIdForApi() {
        return leagueIdForApi;
    }

    public void setLeagueIdForApi(int leagueIdForApi) {
        this.leagueIdForApi = leagueIdForApi;
    }

    public void insertTeam(TeamRepository repository, ApiConfig apiConfig) {

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

                            if (repository.existsByName(String.valueOf(map.get("name")))) {
                                System.out.println("Team exists, no update required");
                            } else {
                                if (leagueIdForApi == 39) {
                                    repository.save(new Teams((String) map.get("name"), (String) map.get("country"), (Integer) map.get("founded"), FALSE, 1, (String) map.get("logo")));
                                } else if (leagueIdForApi == 40) {
                                    repository.save(new Teams((String) map.get("name"), (String) map.get("country"), (Integer) map.get("founded"), FALSE, 2, (String) map.get("logo")));
                                } else if (leagueIdForApi == 41) {
                                    repository.save(new Teams((String) map.get("name"), (String) map.get("country"), (Integer) map.get("founded"), FALSE, 3, (String) map.get("logo")));
                                } else if (leagueIdForApi == 42) {
                                    repository.save(new Teams((String) map.get("name"), (String) map.get("country"), (Integer) map.get("founded"), FALSE, 4, (String) map.get("logo")));
                                } else if (leagueIdForApi == 43) {
                                    repository.save(new Teams((String) map.get("name"), (String) map.get("country"), (Integer) map.get("founded"), FALSE, 5, (String) map.get("logo")));
                                } else {
                                    repository.save(new Teams((String) map.get("name")));
                                }
                            }
                        }
                    }
                }
            } catch (JsonParseException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("-------------------------------------");
        }
    }
    /* TODO Update leagues for promoted / deleted teams (Kind of works in 'IndividualTeamUpdate page' but you need to add the ground in as well which could be a pain */
};

