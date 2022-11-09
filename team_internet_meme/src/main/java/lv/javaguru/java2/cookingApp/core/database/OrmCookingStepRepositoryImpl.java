package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;

import java.util.List;

public class OrmCookingStepRepositoryImpl implements CookingStepRepository {
    @Override
    public void saveCookingSteps(List<CookingStep> cookingSteps, Long recipeId) {

    }

    @Override
    public List<CookingStep> getCookingStepsByRecipeId(Long id) {
        return null;
    }
}
