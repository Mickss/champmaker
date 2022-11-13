package org.micks.champmaker.games;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Table(name = "Games")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long team1;
    private Long team2;
    private String finalScore;
    private LocalDate gameTime;

    public GameEntity(Long team1, Long team2, String finalScore, LocalDate gameTime) {
        this.team1 = team1;
        this.team2 = team2;
        this.finalScore = finalScore;
        this.gameTime = gameTime;
    }
}
