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

    private String name;
    private boolean vegeOption;

    public MealEntity() {
    }

    public MealEntity(String name, boolean vegeOption) {
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
