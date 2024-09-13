package com.groundcollector.admin.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.groundcollector.admin.repository.UpdatesRepository;
import com.groundcollector.model.Teams;
import com.groundcollector.utils.ApiConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class IndividualTeamUpdateParser {

    JsonFactory jsonFactory = new JsonFactory();
    JsonParser jsonParser;
    Teams teams = new Teams();
    HashMap<String, Object> map = new HashMap<String, Object>();

    public String teamNameForApi;
    public int leagueId;
    public int groundId;

    public String getTeamNameForApi() {
        return teamNameForApi;
    }

    public void setTeamNameForApi(String teamNameForApi) {
        this.teamNameForApi = teamNameForApi;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getGroundId() {
        return groundId;
    }

    public void setGroundId(int groundId) {
        this.groundId = groundId;
    }

    public void updateTeam(UpdatesRepository repository, ApiConfig apiConfig) {

        //System.out.println("About to process Team: " + teamNameForApi + " with a League ID:" + leagueId + ", Ground ID: " + groundId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api-football-v1.p.rapidapi.com/v3/teams?name=" + teamNameForApi))
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
                                }
                            }
                            teams.setGroundId(groundId);
                            teams.setLeagueId(leagueId);
                            System.out.println("Team mapping: " + teams.toString());

                            System.out.println("Updating team...");
                            repository.updateTeam(teams);
                            System.out.println("Team updated!");

                            // TODO Currently will only update teams, not add new ones
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
}
