package org.micks.champmaker.meals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {


    @Autowired MealRepository mealRepository;

    public void createMeal(MealDTO mealDTO) {
        MealEntity mealEntity = new MealEntity(mealDTO.getName(), mealDTO.isVegeOption());
        mealRepository.save(mealEntity);
    }
}
