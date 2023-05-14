package org.micks.champmaker.meals;

import javax.validation.constraints.NotNull;

public class MealDTO {

    @NotNull
    private Long champId;
    private String name;
    private boolean vegeOption;

    public MealDTO() {
    }

    public MealDTO(long champId, String name, boolean vegeOption) {
        this.champId = champId;
        this.name = name;
        this.vegeOption = vegeOption;
    }

    public Long getChampId() {
        return champId;
    }

    public String getName() {
        return name;
    }

    public boolean isVegeOption() {
        return vegeOption;
    }
}
