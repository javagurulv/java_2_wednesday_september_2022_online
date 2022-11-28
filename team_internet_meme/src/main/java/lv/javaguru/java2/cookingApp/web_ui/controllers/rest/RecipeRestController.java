package lv.javaguru.java2.cookingApp.web_ui.controllers.rest;

import lv.javaguru.java2.cookingApp.core.dto.requests.*;
import lv.javaguru.java2.cookingApp.core.dto.responses.*;
import lv.javaguru.java2.cookingApp.core.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeRestController {

    @Autowired
    private GetRecipeService getRecipeService;
    @Autowired
    private AddRecipeService addRecipeService;
    @Autowired
    private DeleteRecipeService deleteRecipeService;
    @Autowired
    private UpdateRecipeNameService updateRecipeNameService;
    @Autowired
    private GetAllRecipesService getAllRecipesService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetRecipeResponse getRecipe(@PathVariable Long id) {
        GetRecipeRequest request = new GetRecipeRequest(id);
        return getRecipeService.execute(request);
    }

    @GetMapping(path = "/", produces = "application/json")
    public GetAllRecipesResponse getAllRecipes() {
        return getAllRecipesService.execute(new GetAllRecipesRequest());
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

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public UpdateRecipeNameResponse updateRecipeName(@RequestBody UpdateRecipeNameRequest request, @PathVariable Long id) {
        request.setId(id);
        return updateRecipeNameService.execute(request);
    }
}
