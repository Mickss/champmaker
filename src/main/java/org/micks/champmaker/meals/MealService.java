package org.micks.champmaker.meals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MealService {

    @Autowired
    MealRepository mealRepository;

    public void createMeal(long champId, MealDTO mealDTO) {
        MealEntity mealEntity = new MealEntity(champId, mealDTO.getName(), mealDTO.isVegeOption());
        mealRepository.save(mealEntity);
    }

    public void editMeals(long champId, MealDTO mealDTO) throws EntityNotFoundException {
        Optional<MealEntity> optionalMeal = mealRepository.findById(champId);
        if (optionalMeal.isPresent()) {
            MealEntity mealEntity = optionalMeal.get();
            mealEntity.setName(mealDTO.getName());
            mealEntity.setVegeOption(mealDTO.isVegeOption());
            mealRepository.save(mealEntity);
        } else {
            throw new EntityNotFoundException("Can not find meal with Id: " + champId);
        }
    }

    public List<MealDTO> getMeals(long champId) {
        List<MealEntity> mealList = mealRepository.findAll();
        return mealList.stream()
                .map(mealEntity -> new MealDTO(mealEntity.getChampId(), mealEntity.getName(), mealEntity.isVegeOption()))
                .collect(Collectors.toList());
    }
}
