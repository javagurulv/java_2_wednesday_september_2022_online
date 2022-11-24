package lv.javaguru.java2.cookingApp.web_ui.controllers;

import lv.javaguru.java2.cookingApp.core.dto.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.DeleteRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteRecipeController {

    @Autowired
    private DeleteRecipeService deleteRecipeService;

    @GetMapping("/deleteRecipe")
    public String showDeleteRecipePage(ModelMap modelMap) {
        DeleteRecipeRequest request = new DeleteRecipeRequest();
        modelMap.addAttribute("request", request);
        return "deleteRecipe";
    }

    @PostMapping("/deleteRecipe")
    public String processDeleteRecipeRequest(@ModelAttribute(value = "request") DeleteRecipeRequest request, ModelMap modelMap) {
        DeleteRecipeResponse response = deleteRecipeService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "deleteRecipe";
        }
        return "redirect:/";
    }
}
