package lv.javaguru.java2.cookingApp.web_ui.controllers;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.dto.CookingStepsListDto;
import lv.javaguru.java2.cookingApp.core.dto.IngredientsListDto;
import lv.javaguru.java2.cookingApp.core.dto.AddRecipeDto;
import lv.javaguru.java2.cookingApp.core.dto.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.AddRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AddRecipeController {

    @Autowired
    private AddRecipeService addRecipeService;

    @GetMapping(value = "/addRecipe")
    public String showAddRecipePage(ModelMap modelMap) {

        AddRecipeDto dto = new AddRecipeDto(null, List.of(new Ingredient()), List.of(new CookingStep()));

        modelMap.addAttribute("dto", dto);
        return "addRecipe";
    }

    @PostMapping("/addRecipe")
    public String processAddRecipeRequest(@ModelAttribute(value = "dto") AddRecipeDto dto,
                                          ModelMap modelMap) {
        AddRecipeRequest request = new AddRecipeRequest(dto.getDishName(), dto.getIngredients(), dto.getCookingSteps());
        AddRecipeResponse response = addRecipeService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addRecipe";
        }
        return "redirect:/";
    }
}
