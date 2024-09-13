package com.groundcollector.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Competition {

    @Id
    private int id;
    private String name;
    private String country;
    private int tier;
    private int apiId;
    private String type;

    public Competition(int id, String name, String country, int tier) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.tier = tier;

    }

    public Competition(int id, String name, String country, int tier, int apiId) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.tier = tier;
        this.apiId = apiId;

    }

    public Competition(int id, String name, String country, int tier, String type) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.tier = tier;
        this.type = type;
    }

    public Competition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
