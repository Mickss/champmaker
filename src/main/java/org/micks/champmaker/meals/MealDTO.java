package org.micks.champmaker.meals;

public class MealDTO {

    private String name;
    private boolean vegeOption;

    public MealDTO() {
    }

    public MealDTO(String name, boolean vegeOption) {
        this.name = name;
        this.vegeOption = vegeOption;
    }

    public String getName() {
        return name;
    }

    public boolean isVegeOption() {
        return vegeOption;
    }
}
