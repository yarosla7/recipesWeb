package net.recipesWeb.controllers;

import net.recipesWeb.model.Ingredient;
import net.recipesWeb.services.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private IngredientService ingredientService;

    @PostMapping()
    public void addIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.addIngredient(ingredient);
    }

    @GetMapping("{id}")
    public Ingredient getIngredientById(@PathVariable int id) {
        return ingredientService.getIngredient(id);
    }
}