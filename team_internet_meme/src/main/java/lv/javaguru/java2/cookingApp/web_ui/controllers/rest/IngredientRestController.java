package lv.javaguru.java2.cookingApp.web_ui.controllers.rest;

import lv.javaguru.java2.cookingApp.core.dto.requests.GetAllIngredientsRequest;
import lv.javaguru.java2.cookingApp.core.dto.requests.GetIngredientByIdRequest;
import lv.javaguru.java2.cookingApp.core.dto.requests.SaveIngredientRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.GetAllIngredientsResponse;
import lv.javaguru.java2.cookingApp.core.dto.responses.GetIngredientByIdResponse;
import lv.javaguru.java2.cookingApp.core.dto.responses.SaveIngredientResponse;
import lv.javaguru.java2.cookingApp.core.services.GetAllIngredientsService;
import lv.javaguru.java2.cookingApp.core.services.GetIngredientByIdService;
import lv.javaguru.java2.cookingApp.core.services.SaveIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientRestController {

    @Autowired
    private GetAllIngredientsService getAllIngredientsService;
    @Autowired
    private GetIngredientByIdService getIngredientByIdService;
    @Autowired
    private SaveIngredientService saveIngredientService;

    @GetMapping(value = "/", produces = "application/json")
    public GetAllIngredientsResponse getAllIngredients() {
        return getAllIngredientsService.execute(new GetAllIngredientsRequest());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public GetIngredientByIdResponse getIngredient(@PathVariable Long id) {
        GetIngredientByIdRequest request = new GetIngredientByIdRequest(id);
        return getIngredientByIdService.execute(request);
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public SaveIngredientResponse saveIngredient(@RequestBody SaveIngredientRequest request) {
        return saveIngredientService.execute(request);
    }
}
