package com.groundcollector.admin.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.groundcollector.admin.repository.UpdatesRepository;
import com.groundcollector.model.Grounds;
import com.groundcollector.model.Teams;
import com.groundcollector.utils.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class GroundToTeamMapper {

    @Autowired
    public DataSource dataSource;

    JsonFactory jsonFactory = new JsonFactory();
    JsonParser jsonParser;
    Teams teams = new Teams();
    Grounds grounds = new Grounds();
    HashMap<String, Object> map = new HashMap<String, Object>();

    public int leagueIdForApi;

    public int getLeagueIdForApi() {
        return leagueIdForApi;
    }

    public void setLeagueIdForApi(int leagueIdForApi) {
        this.leagueIdForApi = leagueIdForApi;
    }

    public void matchGroundsToTeams(UpdatesRepository updatesRepository, ApiConfig apiConfig) {

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
                                if (nameField.equals("name")) {
                                    teams.setName(jsonParser.getValueAsString());
                                    map.put("teamName", teams.getName());
                                }

                            }
                        } else if (fieldName.equals("venue")) {

                            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                                String nameField = jsonParser.getCurrentName();
                                jsonParser.nextToken();
                                if (nameField.equals("name")) {
                                    grounds.setName(jsonParser.getValueAsString());
                                    map.put("groundName", grounds.getName());
                                    System.out.println("Mapping: " + map);

                                    System.out.println("Ground to find ID for: "+ grounds.getName());
                                    updatesRepository.mapGround(teams, grounds.getName());

                                        System.out.println("Team: " + teams.getName());
                                        System.out.println("Ground ID: " + teams.getGroundId());
                                        System.out.println("-----");
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
        }
    }
}
