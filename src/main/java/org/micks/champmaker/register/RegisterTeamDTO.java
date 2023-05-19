package org.micks.champmaker.register;

public class RegisterTeamDTO {
    private long teamId;
    private String registrationDate;

    public RegisterTeamDTO() {
    }

    public RegisterTeamDTO(long teamId, String registrationDate) {
        this.teamId = teamId;
        this.registrationDate = registrationDate;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }
}
