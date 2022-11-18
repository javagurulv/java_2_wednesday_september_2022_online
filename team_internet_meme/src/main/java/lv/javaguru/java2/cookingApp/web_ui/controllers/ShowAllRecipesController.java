package lv.javaguru.java2.cookingApp.web_ui.controllers;

import lv.javaguru.java2.cookingApp.core.dto.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.GetAllRecipesResponse;
import lv.javaguru.java2.cookingApp.core.services.GetAllRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllRecipesController {

    @Autowired
    private GetAllRecipesService getAllRecipesService;

    @GetMapping(value = "/showAllRecipes")
    public String showAllRecipes(ModelMap modelMap) {
        GetAllRecipesResponse response = getAllRecipesService.execute(
                new GetAllRecipesRequest()
        );
        modelMap.addAttribute("recipes", response.getRecipes());
        return "/showAllRecipes";
    }
}
