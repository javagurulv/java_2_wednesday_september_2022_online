package lv.javaguru.java2.cookingApp.web_ui.controllers;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.dto.CookingStepsListDto;
import lv.javaguru.java2.cookingApp.core.dto.IngredientsListDto;
import lv.javaguru.java2.cookingApp.core.dto.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.AddRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddRecipeController {

    @Autowired
    private AddRecipeService addRecipeService;

    @GetMapping(value = "/addRecipe")
    public String showAddRecipePage(ModelMap modelMap) {
        IngredientsListDto ingredientsForm = new IngredientsListDto();
        ingredientsForm.addIngredient(new Ingredient());
        CookingStepsListDto cookingStepsForm = new CookingStepsListDto();
        cookingStepsForm.addCookingStep(new CookingStep());

        modelMap.addAttribute("request", new AddRecipeRequest());
        modelMap.addAttribute("ingredients", ingredientsForm);
        modelMap.addAttribute("cookingSteps", cookingStepsForm);
        return "addRecipe";
    }

    @PostMapping("/addRecipe")
    public String processAddRecipeRequest(@ModelAttribute(value = "request") AddRecipeRequest request,
                                          @ModelAttribute(value = "ingredients") IngredientsListDto ingredientsListDto,
                                          @ModelAttribute(value = "cookingSteps") CookingStepsListDto cookingStepsListDto,
                                          ModelMap modelMap) {
        if (!ingredientsListDto.getIngredients().isEmpty()) {
            request.setIngredients(ingredientsListDto.getIngredients());
        }
        if (!cookingStepsListDto.getCookingSteps().isEmpty()) {
            request.setCookingSteps(cookingStepsListDto.getCookingSteps());
        }
        AddRecipeResponse response = addRecipeService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addRecipe";
        }
        return "redirect:/";
    }
}
