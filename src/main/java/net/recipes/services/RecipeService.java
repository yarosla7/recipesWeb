package net.recipes.services;

import net.recipes.model.Recipe;

import java.util.Map;

public interface RecipeService {

    Long addRecipe(Recipe recipe);

    Recipe getRecipe(Long id);

    Recipe editRecipe(Long id, Recipe recipe);

    boolean deleteRecipe(Long id);

    Map<Long, Recipe> getAllRecipes();
}