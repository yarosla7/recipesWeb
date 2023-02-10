package net.recipesWeb.controllers;

import net.recipesWeb.model.Ingredient;
import net.recipesWeb.services.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping()
    public void addIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.addIngredient(ingredient);
    }

    @GetMapping("{id}")
    public Ingredient getIngredientById(@PathVariable int id) {
        return ingredientService.getIngredient(id);
    }
}