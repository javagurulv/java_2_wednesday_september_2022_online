package lv.javaguru.java2.cookingApp.web_ui.controllers;


import lv.javaguru.java2.cookingApp.core.dto.FormView;
import lv.javaguru.java2.cookingApp.core.dto.SearchRecipesDto;
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
        SearchRecipesDto dto = new SearchRecipesDto(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            dto.add(new FormView());
        }
        modelMap.addAttribute("dto", dto);
        return "searchRecipes";
    }

    @PostMapping("/searchRecipes")
    public String processSearchRequest(@ModelAttribute(value = "dto") SearchRecipesDto dto, ModelMap modelMap) {
        List<FormView> names = dto.getList().stream().filter(s -> !s.getName().isBlank()).collect(Collectors.toList());
        List<String> ingredients = new ArrayList<>();
        for (FormView s : names) {
            ingredients.add(s.getName());
        }
        SearchRecipeResponse response = service.execute(new SearchRecipeRequest(ingredients));
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "searchRecipes";
        }
        modelMap.addAttribute("recipes", response.getRecipes());
        return "searchRecipes";
    }
}
