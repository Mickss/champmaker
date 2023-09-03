package org.micks.champmaker.championships;

import org.micks.champmaker.register.RegisterTeamDTO;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ChampionshipDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String city;
    private Date date;
    private List<RegisterTeamDTO> registeredTeams;

    public ChampionshipDTO() {
    }

    public ChampionshipDTO(Long id, String name, String city, Date date, List<RegisterTeamDTO> registeredTeams) {
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

    public Date getDate() {
        return date;
    }

    public List<RegisterTeamDTO> getRegisteredTeams() {
        return registeredTeams;
    }
}
