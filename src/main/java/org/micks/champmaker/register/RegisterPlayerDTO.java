package org.micks.champmaker.register;

public class RegisterPlayerDTO {
    private Long playerId;
    private Long mealId;
    private boolean parentalApproval;

    public RegisterPlayerDTO() {
    }

    public RegisterPlayerDTO(long playerId, long mealId) {
        this.mealId = mealId;
        this.playerId = playerId;
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
}
