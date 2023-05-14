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

    public void editMeal(long champId, long mealId, MealDTO mealDTO) throws EntityNotFoundException {
        Optional<MealEntity> optionalMeal = mealRepository.findById(mealId);
        if (optionalMeal.isPresent()) {
            MealEntity mealEntity = optionalMeal.get();
            if (champId != mealEntity.getChampId()) {
                throw new IllegalArgumentException("Invalid champId: " + champId);
            }
            mealEntity.setName(mealDTO.getName());
            mealEntity.setVegeOption(mealDTO.isVegeOption());
            mealRepository.save(mealEntity);
        } else {
            throw new EntityNotFoundException("Can not find meal with Id: " + mealId);
        }
    }

    public List<MealDTO> getMeals(long champId) {
        List<MealEntity> mealList = mealRepository.findByChampId(champId);
        return mealList.stream()
                .map(mealEntity -> new MealDTO(mealEntity.getChampId(), mealEntity.getName(), mealEntity.isVegeOption()))
                .collect(Collectors.toList());
    }

    public MealDTO getMeal(long champId, long mealId) {
        Optional<MealEntity> optionalMeal = mealRepository.findById(mealId);
        if (optionalMeal.isEmpty()) {
            throw new EntityNotFoundException("Can not find meal with Id: " + mealId);
        }
        MealEntity mealEntity = optionalMeal.get();
        if (champId != mealEntity.getChampId()) {
            throw new IllegalArgumentException("Invalid champId: " + champId);
        }
        return new MealDTO(mealEntity.getChampId(), mealEntity.getName(), mealEntity.isVegeOption());
    }
}
