package org.micks.champmaker.register;

public class RegisterPlayerDTO {
    private Long playerId;
    private Long mealId;

    public RegisterPlayerDTO() {
    }

    public RegisterPlayerDTO(long playerId, long mealId) {
        this.mealId = mealId;
        this.playerId = playerId;
    }

    public Long getMealId() {
        return mealId;
    }

    public Long getPlayerId() {
        return playerId;
    }
}
