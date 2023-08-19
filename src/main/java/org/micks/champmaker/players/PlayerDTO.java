package org.micks.champmaker.players;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PlayerDTO {

    private Long id;

    private Long teamId;

    @NotNull
    @Size(min = 2)
    private String name;

    private int shirtNumber;

    @NotNull
    private Date birthDate;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, Long teamId, String name, int shirtNumber, Date birthDate) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
