package com.groundcollector.model;

import javax.persistence.*;

@Entity
    public class Teams {
        @Id
        @GeneratedValue(strategy= GenerationType.SEQUENCE)
        private int id;
        private String name;
        private String country;
        private Integer founded;
        private Boolean nation;
        private String logo;

        @Column(name = "league_id")
        private int leagueId;

        @Column(name = "ground_id")
        private int groundId;

        public Teams(String name) {
            this.name = name;
        }

        public Teams(int id, String name, int leagueId) {
            this.id = id;
            this.name = name;
            this.leagueId = leagueId;
        }

        public Teams(int id, String name, String logo) {
            this.id = id;
            this.name = name;
            this.logo = logo;
        }

        public Teams(int id, String name, String logo, int leagueId) {
            this.id = id;
            this.name = name;
            this.logo = logo;
            this.leagueId = leagueId;
        }

        public Teams(String name, String country, Integer founded, Boolean nation, int leagueId) {
            this.name = name;
            this.country = country;
            this.founded = founded;
            this.nation = nation;
            this.leagueId = leagueId;
        }

        public Teams(String name, String country, Integer founded, Boolean nation, int leagueId, String logo) {
            this.name = name;
            this.country = country;
            this.founded = founded;
            this.nation = nation;
            this.leagueId = leagueId;
            this.logo = logo;
        }

        public Teams(String name, String country, Integer founded, Boolean nation, int leagueId, int groundId) {
            this.name = name;
            this.country = country;
            this.founded = founded;
            this.nation = nation;
            this.leagueId = leagueId;
            this.groundId = groundId;
        }

        public Teams() {

        }

        public Teams(int id, String name) {
            this.id = id;
            this.name = name;
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

        public Integer getFounded() {
            return founded;
        }

        public void setFounded(Integer founded) {
            this.founded = founded;
        }

        public Boolean getNation() {
            return nation;
        }

        public void setNation(Boolean nation) {
            this.nation = nation;
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        @Override
        public String toString() {
            return "Teams{" +
                    "name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    ", founded=" + founded +
                    ", nation=" + nation +
                    ", leagueId=" + leagueId +
                    ", groundId=" + groundId +
                    '}';
        }

    }