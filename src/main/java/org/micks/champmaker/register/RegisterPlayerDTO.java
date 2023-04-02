package org.micks.champmaker.register;

public class RegisterPlayerDTO {
    private Long playerId;

    public RegisterPlayerDTO() {
    }

    public RegisterPlayerDTO(long playerId){
        this.playerId = playerId;
    }

    public Long getPlayerId() {
        return playerId;
    }
}
