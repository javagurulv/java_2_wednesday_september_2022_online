package lv.javaguru.java2.cookingApp.web_ui.controllers;

import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.AddRecipeResponse;
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
        modelMap.addAttribute("request", new AddRecipeRequest());
        return "addRecipe";
    }

    @PostMapping("/addRecipe")
    public String processAddRecipeRequest(@ModelAttribute(value = "request") AddRecipeRequest request, ModelMap modelMap) {
        AddRecipeResponse response = addRecipeService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addRecipe";
        }
        return "redirect:/";
    }
}
