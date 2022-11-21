package lv.javaguru.java2.cookingApp.web_ui.controllers;


import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.dto.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.SearchRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchRecipesController {

    @Autowired
    private SearchRecipeService service;

    @GetMapping("/searchRecipes")
    public String showSearchPage(ModelMap modelMap) {
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ingredients.add("");
        }
        modelMap.addAttribute("request", new SearchRecipeRequest(ingredients));
        return "searchRecipes";
    }

    @PostMapping("/searchRecipes")
    public String processSearchRequest(@ModelAttribute(value = "request") SearchRecipeRequest request, ModelMap modelMap) {
        List<String> ingredients = request.getIngredientNameList().stream().filter(s -> !s.isBlank()).collect(Collectors.toList());
        SearchRecipeResponse response = service.execute(new SearchRecipeRequest(ingredients));
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "searchRecipes";
        }
        modelMap.addAttribute("recipes", response.getRecipes());
        return "searchRecipes";
    }
}
