package org.micks.champmaker.championships;

import java.util.List;

public class ScheduleChampionshipRequest {

    private Long champId;
    private List<Object> testList;

    public ScheduleChampionshipRequest(Long champId, List<Object> testList) {
        this.champId = champId;
        this.testList = testList;
    }

    public ScheduleChampionshipRequest() {
    }

    public Long getChampId() {
        return champId;
    }

    public void setChampId(Long champId) {
        this.champId = champId;
    }

    public List<Object> getTestList() {
        return testList;
    }

    public void setTestList(List<Object> testList) {
        this.testList = testList;
    }
}
