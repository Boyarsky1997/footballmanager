package com.github.boyarsky1997.footballmanager.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "football_players")
public class Footballer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int footballerId;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "age")
    private String age;

    @Column(name = "early_career")
    private LocalDate earlyCareer;

    @Column(name = "transfer_price")
    private int transferPrice;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Footballer() {
    }

    public Footballer(String name, String firstName, String age, LocalDate earlyCareer, int transferPrice) {
        this.name = name;
        this.firstName = firstName;
        this.age = age;
        this.earlyCareer = earlyCareer;
        this.transferPrice = transferPrice;
    }

    public int getFootballerId() {
        return footballerId;
    }

    public void setFootballerId(int footballerId) {
        this.footballerId = footballerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public LocalDate getEarlyCareer() {
        return earlyCareer;
    }

    public void setEarlyCareer(LocalDate earlyCareer) {
        this.earlyCareer = earlyCareer;
    }

    public int getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(int transferPrice) {
        this.transferPrice = transferPrice;
    }

    @Override
    public String toString() {
        return "Footballer{" +
                "footballerId=" + footballerId +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age='" + age + '\'' +
                ", earlyCareer=" + earlyCareer +
                ", transferPrice=" + transferPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Footballer that = (Footballer) o;
        return footballerId == that.footballerId &&
                transferPrice == that.transferPrice &&
                Objects.equals(name, that.name) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(age, that.age) &&
                Objects.equals(earlyCareer, that.earlyCareer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(footballerId, name, firstName, age, earlyCareer, transferPrice);
    }
}
