package com.github.boyarsky1997.footballmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int team_id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "team_account")
    private Double teamAccount;


    public Team() {
    }

    public Team(String name, String city, String country, Double teamAccount) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.teamAccount = teamAccount;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getTeamAccount() {
        return teamAccount;
    }

    public void setTeamAccount(Double teamAccount) {
        this.teamAccount = teamAccount;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + team_id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", teamAccount=" + teamAccount +
                '}';
    }
}
