package net.recipesWeb.services;

import net.recipesWeb.model.Recipe;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);
}
