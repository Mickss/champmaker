package org.micks.champmaker.teams;

import org.micks.champmaker.players.PlayerEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "team")
@Entity
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<PlayerEntity> players;

    public TeamEntity() {
    }

    public TeamEntity(String nameTeam) {
        this.name = nameTeam;
    }

    public String getName() {
        return name;
    }
}
