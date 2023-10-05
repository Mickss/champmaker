package org.micks.champmaker.championships;

public class ScheduleChampionshipRequest {

    public Long champId;

    public ScheduleChampionshipRequest(Long champId) {
        this.champId = champId;
    }

    public ScheduleChampionshipRequest() {
    }

    public Long getChampId() {
        return champId;
    }

    public void setChampId(Long champId) {
        this.champId = champId;
    }
}
