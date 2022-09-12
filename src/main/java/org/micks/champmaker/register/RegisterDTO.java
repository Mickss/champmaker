package org.micks.champmaker.register;

public class RegisterDTO {
    private long teamId;
    private String registrationDate;

    public RegisterDTO() {
    }

    public RegisterDTO(long teamId, String registrationDate) {
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