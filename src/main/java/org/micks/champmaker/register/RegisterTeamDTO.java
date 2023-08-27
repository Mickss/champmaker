package org.micks.champmaker.register;

import java.util.Date;

public class RegisterTeamDTO {
    private long teamId;
    private Date registrationDate;

    public RegisterTeamDTO() {
    }

    public RegisterTeamDTO(long teamId, Date registrationDate) {
        this.teamId = teamId;
        this.registrationDate = registrationDate;
    }

    public long getTeamId() {
        return teamId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
}
