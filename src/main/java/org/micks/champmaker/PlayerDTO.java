package org.micks.champmaker;

public class PlayerDTO {

    private String playerName;
    private Long playerNumber;
    private String playerTeam;

    public PlayerDTO() {
    }

    public PlayerDTO(String playerName, Long playerNumber, String playerTeam) {
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerTeam = playerTeam;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Long getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerTeam() {
        return playerTeam;
    }
}
