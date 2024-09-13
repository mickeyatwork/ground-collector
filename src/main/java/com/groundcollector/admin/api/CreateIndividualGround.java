package com.groundcollector.admin.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.groundcollector.model.Grounds;
import com.groundcollector.utils.ApiConfig;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateIndividualGround {

    JsonFactory jsonFactory = new JsonFactory();
    JsonParser jsonParser;
    Grounds grounds = new Grounds();
    HashMap<String, String> map = new HashMap<String, String>();
    ArrayList<Grounds> searchResults = new ArrayList<>();

    public String searchTerm;

    public CreateIndividualGround() {
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void addGround(ApiConfig apiConfig) {

        HttpRequest request = HttpRequest.newBuilder()
               // .uri(URI.create("https://api-football-v1.p.rapidapi.com/v3/venues?search=" + searchTerm))
                .uri(URI.create("https://api-football-v1.p.rapidapi.com/v3/venues?search=kassam"))
                .header(apiConfig.getApiKeyHeader(), apiConfig.getApiKey())
                .header(apiConfig.getApiHostHeader(), apiConfig.getApiHost())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;

        {

            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());

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
                        if (fieldName.equals("id")) {
                            while (jsonParser.nextToken() != JsonToken.END_OBJECT ) {
                                String nameField = jsonParser.getCurrentName();
                                jsonParser.nextToken();
                                if (nameField.equals("name")) {

                                    grounds.setName(jsonParser.getValueAsString());
                                    map.put("name", grounds.getName());

                                } else if (nameField.equals("capacity")) {
                                    grounds.setCapacity(jsonParser.getValueAsString());
                                    map.put("capacity", grounds.getCapacity());

                                } else if (nameField.equals("city")) {
                                    grounds.setCity(jsonParser.getValueAsString());
                                    map.put("city", grounds.getCity());

                                }

                            }
                            grounds.setActive(true);
                            System.out.println("Ground.toString: " + grounds.toString());
                            System.out.println("Ground mapping: " + map);
                            /*if (repository.existsByName(String.valueOf(map.get("name")))) {
                                System.out.println("Ground already exists");
                            }
                            else {
                                repository.save(new Grounds(map.get("name"), map.get("capacity"), TRUE, map.get("city")));
                            }
                             */
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
