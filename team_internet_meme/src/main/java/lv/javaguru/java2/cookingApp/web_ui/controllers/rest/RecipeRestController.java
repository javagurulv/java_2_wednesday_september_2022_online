package lv.javaguru.java2.cookingApp.web_ui.controllers.rest;

import lv.javaguru.java2.cookingApp.core.dto.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.requests.GetRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.core.dto.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.core.dto.responses.GetRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.AddRecipeService;
import lv.javaguru.java2.cookingApp.core.services.DeleteRecipeService;
import lv.javaguru.java2.cookingApp.core.services.GetRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeRestController {

    @Autowired
    private GetRecipeService getRecipeService;
    @Autowired
    private AddRecipeService addRecipeService;
    @Autowired private DeleteRecipeService deleteRecipeService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetRecipeResponse getRecipe(@PathVariable Long id) {
        GetRecipeRequest request = new GetRecipeRequest(id);
        return getRecipeService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddRecipeResponse addRecipe(@RequestBody AddRecipeRequest request) {
        return addRecipeService.execute(request);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeleteRecipeResponse deleteRecipe(@PathVariable Long id) {
        DeleteRecipeRequest request = new DeleteRecipeRequest(id);
        return deleteRecipeService.execute(request);
    }
}
