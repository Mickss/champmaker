package org.micks.champmaker.players;

import javax.validation.constraints.NotNull;

public class PlayerDTO {

    private Long id;

    private Long teamId;

    @NotNull
    private String name;

    private Long shirtNumber;

    @NotNull
    private Long age;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, Long teamId, String name, Long shirtNumber, Long age) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.age = age;
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

    public Long getShirtNumber() {
        return shirtNumber;
    }

    public Long getAge() {
        return age;
    }
}
