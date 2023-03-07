package org.micks.champmaker.players;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@NoArgsConstructor
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;
    private Long playerNumber;
    private Long playerYear;

    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private TeamEntity team;

    public PlayerEntity(String playerName, Long playerNumber, Long playerYear, TeamEntity team) {
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerYear = playerYear;
        this.team = team;
    }
}
