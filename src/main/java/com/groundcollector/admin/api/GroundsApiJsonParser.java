package com.groundcollector.admin.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.groundcollector.admin.repository.GroundRepository;
import com.groundcollector.model.Grounds;
import com.groundcollector.repository.GroundsRepository;
import com.groundcollector.repository.GroundsRepositoryImpl;
import com.groundcollector.utils.ApiConfig;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import static java.lang.Boolean.TRUE;

public class GroundsApiJsonParser {
    JsonFactory jsonFactory = new JsonFactory();
    JsonParser jsonParser;
    Grounds grounds = new Grounds();
    HashMap<String, String> map = new HashMap<String, String>();

    public int leagueIdForApi;

    public int getLeagueIdForApi() {
        return leagueIdForApi;
    }

    public void setLeagueIdForApi(int leagueIdForApi) {
        this.leagueIdForApi = leagueIdForApi;
    }

    public void insertGround(GroundRepository repository, JdbcTemplate template, ApiConfig apiConfig) {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api-football-v1.p.rapidapi.com/v3/teams?league=" + leagueIdForApi + "&season=2024"))
                //.uri(URI.create("https://api-football-v1.p.rapidapi.com/v3/teams?league=41&season=2022"))
                .header(apiConfig.getApiKeyHeader(), apiConfig.getApiKey())
                .header(apiConfig.getApiHostHeader(), apiConfig.getApiHost())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        {
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
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
                    //System.out.println(token + ": " + jsonParser.getCurrentName());

                    if (token.equals(JsonToken.FIELD_NAME)) {
                        String fieldName = jsonParser.getCurrentName();
                        jsonParser.nextToken();
                        if (fieldName.equals("venue")) {

                            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                                String nameField = jsonParser.getCurrentName();
                                jsonParser.nextToken();
                                if (nameField.equals("id")) {
                                    //grounds.setId(jsonParser.getIntValue());
                                } else if (nameField.equals("name")) {

                                    grounds.setName(jsonParser.getValueAsString());
                                    map.put("name", grounds.getName());

                                } else if (nameField.equals("capacity")) {
                                    grounds.setCapacity(jsonParser.getValueAsString());
                                    map.put("capacity", grounds.getCapacity());

                                }
                                else if (nameField.equals("city")) {
                                    grounds.setCity(jsonParser.getValueAsString());
                                    map.put("city", grounds.getCity());

                                }
                                else if (nameField.equals("image")) {
                                    grounds.setImage(jsonParser.getValueAsString());
                                    map.put("image", grounds.getImage());

                                }

                            }
                            grounds.setActive(true);
                            System.out.println("Ground mapping: " + map);

                            GroundsRepository groundsRepository = new GroundsRepositoryImpl();

                            /* TODO work being done here to update an existing ground
                            if (repository.existsByName(String.valueOf(map.get("name")))) {

                                Grounds originalGround = new Grounds();
                                groundsRepository.findGroundDetailsByName(originalGround, grounds.getName(), template);
                                grounds.setId(originalGround.getId());
                                grounds.setLat(originalGround.getLat());
                                grounds.setLng(originalGround.getLng());

                                System.out.println("Checking grounds to string before finding db info: " + grounds.toString());
                                System.out.println("originalGround toString: " + originalGround);
                                System.out.println("Grounds info after querying: " + grounds.toString());

                                if (!grounds.getCapacity().equals(originalGround.getCapacity()) || !grounds.getImage().equals(originalGround.getImage())) {
                                    System.out.println("Show the ground ID/name and then the details being updated:");
                                    System.out.println("Ground ID: " + grounds.getId());
                                    System.out.println("Name: " + grounds.getName());
                                    System.out.println("Capacity: " + grounds.getCapacity());
                                    System.out.println("Image: " + grounds.getImage());
                                    System.out.println("Lat: " + grounds.getLat());
                                    System.out.println("Lng: " + grounds.getLng());
                                    groundsRepository.updateGroundDetails(grounds, grounds.getId(), template );

                                }
                                else {

                                    System.out.println("Ground exists, capacity and image are the same as previous, so no update required");
                                }
                            }
                            // TODO If ground name has changed we need to update this and move the old name to 'aliases' for that ground
                            else if (!repository.existsByName(String.valueOf(map.get("name")))){


                                if (groundsRepository.groundDetailsAllMatch(grounds, String.valueOf(map.get("name")), template)) {

                                        Grounds oldGroundDetails = new Grounds();
                                        oldGroundDetails.setCapacity(grounds.getCapacity());
                                        oldGroundDetails.setCity(grounds.getCity());

                                        groundsRepository.findGroundNameByDetails(oldGroundDetails, template);
                                        System.out.println("Key update details ahead of update");
                                        System.out.println("Old Ground details: " + oldGroundDetails.toString());
                                        System.out.println("Ground details: " + grounds.toString());
                                        groundsRepository.updateGroundName(oldGroundDetails, String.valueOf(map.get("name")), template);

                                    }
                            } else {
                             */
                                repository.save(new Grounds(map.get("name"), map.get("capacity"), TRUE, map.get("city"), map.get("image")));
                           // }
                        }
                    }
                }
            } catch (JsonParseException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("-------------------------------------");
            //System.out.println(groundsList);
        }
    }
}






