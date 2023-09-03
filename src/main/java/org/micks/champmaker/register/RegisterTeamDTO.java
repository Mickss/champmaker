package org.micks.champmaker.register;

import java.util.Date;

public class RegisterTeamDTO {
    private long teamId;
    private String name;
    private Date registrationDate;
    private String champGroup;


    public RegisterTeamDTO() {
    }

    public RegisterTeamDTO(long teamId, String name, Date registrationDate, String champGroup) {
        this.teamId = teamId;
        this.name = name;
        this.registrationDate = registrationDate;
        this.champGroup = champGroup;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getChampGroup() {
        return champGroup;
    }
}
