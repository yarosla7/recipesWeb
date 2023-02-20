package net.recipes.services;

import net.recipes.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface RecipeService {

    Long addRecipe(Recipe recipe);

    Recipe getRecipe(Long id);

    Recipe editRecipe(Long id, Recipe recipe);

    boolean deleteRecipe(Long id);

    void deleteAllRecipes();

    Map<Long, Recipe> getAllRecipes();

    File recipesToTxt() throws IOException;
}