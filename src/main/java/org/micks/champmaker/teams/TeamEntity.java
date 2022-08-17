package org.micks.champmaker.teams;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@NoArgsConstructor
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameTeam;

    @OneToMany(mappedBy = "team")
    private List<PlayerEntity> players;

    public TeamEntity(String nameTeam) {
        this.nameTeam = nameTeam;
    }
}
