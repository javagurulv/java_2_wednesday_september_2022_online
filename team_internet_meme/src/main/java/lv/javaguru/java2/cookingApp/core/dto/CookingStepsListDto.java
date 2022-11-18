package lv.javaguru.java2.cookingApp.core.dto;

import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CookingStepsListDto {
    private List<CookingStep> cookingSteps;

    public CookingStepsListDto() {
        cookingSteps = new ArrayList<>();
    }


    public void addCookingStep(CookingStep cookingStep) {
        this.cookingSteps.add(cookingStep);
    }

}
