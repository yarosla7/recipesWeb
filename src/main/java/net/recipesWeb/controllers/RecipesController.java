package net.recipesWeb.controllers;

import net.recipesWeb.model.Recipe;
import net.recipesWeb.services.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping()
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @GetMapping("{id}")
    public Recipe getRecipeById(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }
}
