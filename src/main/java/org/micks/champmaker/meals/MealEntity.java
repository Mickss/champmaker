package org.micks.champmaker.meals;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "meal")
@Entity
public class MealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long champId;
    private String name;
    private boolean vegeOption;

    public MealEntity() {
    }

    public MealEntity(long champId, String name, boolean vegeOption) {
        this.champId = champId;
        this.name = name;
        this.vegeOption = vegeOption;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVegeOption(boolean vegeOption) {
        this.vegeOption = vegeOption;
    }

    public long getChampId() {
        return champId;
    }

    public String getName() {
        return name;
    }

    public boolean isVegeOption() {
        return vegeOption;
    }
}
