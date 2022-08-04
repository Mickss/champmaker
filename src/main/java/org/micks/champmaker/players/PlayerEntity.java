package org.micks.champmaker.players;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String playerTeam;


    public PlayerEntity(String playerName, Long playerNumber, String playerTeam) {
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerTeam = playerTeam;
    }
}
