package org.micks.champmaker.championships;

import java.util.List;

public class ChampionshipDTO {

    private String name;
    private String city;
    private String date;
    private List<Long> registeredTeams;

    public ChampionshipDTO() {
    }

    public ChampionshipDTO(String name, String city, String date, List<Long> registeredTeams) {
        this.name = name;
        this.city = city;
        this.date = date;
        this.registeredTeams = registeredTeams;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public List<Long> getRegisteredTeams() {
        return registeredTeams;
    }
}
