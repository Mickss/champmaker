package org.micks.champmaker.games;

public class GameDTO {

    private Long team1;
    private Long team2;
    private String finalScore;
    private String gameTime;

    public GameDTO() {
    }

    public GameDTO(Long team1, Long team2, String finalScore, String gameTime) {
        this.team1 = team1;
        this.team2 = team2;
        this.finalScore = finalScore;
        this.gameTime = gameTime;
    }

    public Long getTeam1() {
        return team1;
    }

    public Long getTeam2() {
        return team2;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public String getGameTime() {
        return gameTime;
    }
}
