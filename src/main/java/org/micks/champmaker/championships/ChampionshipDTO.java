package org.micks.champmaker.championships;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ChampionshipDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String city;
    private String date;
    private List<Long> registeredTeams;

    public ChampionshipDTO() {
    }

    public ChampionshipDTO(Long id, String name, String city, String date, List<Long> registeredTeams) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.date = date;
        this.registeredTeams = registeredTeams;
    }

    public Long getId() {
        return id;
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
