package org.micks.champmaker.players;

public class PlayerDTO {

    private String playerName;
    private Long playerNumber;
    private Long playerYear;
    private Long teamId;

    public PlayerDTO() {
    }

    public PlayerDTO(String playerName, Long playerNumber, Long playerYear, Long teamId) {
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerYear = playerYear;
        this.teamId = teamId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Long getPlayerNumber() {
        return playerNumber;
    }

    public Long getPlayerYear() {
        return playerYear;
    }

    public Long getTeamId() {
        return teamId;
    }
}
