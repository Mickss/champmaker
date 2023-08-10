package org.micks.champmaker.register;

import org.micks.champmaker.players.PlayerDTO;

public class RegisterPlayerDTO {
    private Long playerId;
    private Long mealId;
    private boolean parentalApproval;
    private PlayerDTO playerInfo;

    public RegisterPlayerDTO() {
    }

    public RegisterPlayerDTO(long playerId, Long mealId, PlayerDTO playerInfo) {
        this.mealId = mealId;
        this.playerId = playerId;
        this.playerInfo = playerInfo;
    }

    public RegisterPlayerDTO(Long playerId, Long mealId, boolean parentalApproval) {
        this.playerId = playerId;
        this.mealId = mealId;
        this.parentalApproval = parentalApproval;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getMealId() {
        return mealId;
    }

    public boolean isParentalApproval() {
        return parentalApproval;
    }

    public PlayerDTO getPlayerInfo() {
        return playerInfo;
    }
}
