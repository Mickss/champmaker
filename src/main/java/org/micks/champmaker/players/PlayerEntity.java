package org.micks.champmaker.players;

import org.micks.champmaker.teams.TeamEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "player")
@Entity
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long shirtNumber;
    private Long age;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private TeamEntity team;

    public PlayerEntity() {
    }

    public PlayerEntity(String name, Long shirtNumber, Long age, TeamEntity team) {
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.age = age;
        this.team = team;
    }

    public Long getId() {
        return id;
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

    public TeamEntity getTeam() {
        return team;
    }
}
