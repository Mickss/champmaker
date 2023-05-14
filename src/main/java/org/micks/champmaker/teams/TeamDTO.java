package org.micks.champmaker.teams;

import javax.validation.constraints.NotNull;

public class TeamDTO {

    private Long id;

    @NotNull
    private String name;

    public TeamDTO() {
    }

    public TeamDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
