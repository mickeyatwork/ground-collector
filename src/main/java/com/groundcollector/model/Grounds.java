package com.groundcollector.model;


import javax.persistence.*;

@Entity
public class Grounds {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String capacity;
    private Integer built;
    private Boolean active;
    private String city;
    @Transient
    private String homeTeam;
    private String lat;
    private String lng;
    private String image;
    private String aliases;
    @Transient
    private String homeTeamImage;

    public Grounds(String name, String capacity, Integer built, Boolean active) {
        this.name = name;
        this.capacity = capacity;
        this.built = built;
        this.active = active;
    }

    public Grounds(Integer id, String name, String capacity, Integer built, Boolean active) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.built = built;
        this.active = active;
    }

    public Grounds(String name, String capacity, Boolean active, String city) {
        this.name = name;
        this.capacity = capacity;
        this.active = active;
        this.city = city;
    }

    public Grounds(String name, String capacity, Boolean active, String city, String image) {
        this.name = name;
        this.capacity = capacity;
        this.active = active;
        this.city = city;
        this.image = image;
    }

    public Grounds(String name, String capacity, Boolean active) {
        this.name = name;
        this.capacity = capacity;
        this.active = active;
    }

    public Grounds(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Grounds(Integer id, String name, String homeTeam) {
        this.id = id;
        this.name = name;
        this.homeTeam = homeTeam;
    }

    public Grounds(Integer id, String name, String lat, String lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public Grounds(Integer id, String name, String lat, String lng, String capacity, String city, String homeTeam) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.capacity = capacity;
        this.city = city;
        this.homeTeam = homeTeam;
    }

    public Grounds(Integer id, String name, String lat, String lng, String capacity, String city, String homeTeam, String homeTeamImage) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.capacity = capacity;
        this.city = city;
        this.homeTeam = homeTeam;
        this.homeTeamImage = homeTeamImage;
    }

    public Grounds(String capacity, String image) {
        this.capacity = capacity;
        this.image = image;
    }

    public Grounds(String name) {
        this.name = name;
    }

    public Grounds() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Integer getBuilt() {
        return built;
    }

    public void setBuilt(Integer built) {
        this.built = built;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String logo) {
        this.image = logo;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getHomeTeamImage() {
        return homeTeamImage;
    }

    public void setHomeTeamImage(String homeTeamImage) {
        this.homeTeamImage = homeTeamImage;
    }

    public String toString() {
        return "Ground{" +
                //"id=" + id +
                ", name='" + name + '\'' +
                ", capacity='" + capacity + '\'' +
                ", built='" + built + '\'' +
                ", active=" + active +
                ", lat=" + lat +
                ", lng=" + lng +
                ", image=" + image +
                '}';
    }
}