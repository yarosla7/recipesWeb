package net.recipes.services.impl;

import net.recipes.model.Recipe;
import net.recipes.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final Map<Long, Recipe> recipeMap = new HashMap<>();
    private static long id = 0;

    //добавление рецепта в мапу рецептов:
    @Override
    public long addRecipe(Recipe recipe) {
        recipeMap.put(id, recipe);
        return id++;
    }

    //получение рецепта по айди:
    @Override
    public Recipe getRecipe(long id) {
        return recipeMap.get(id);
    }

    //редактирование рецепта:
    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            return recipe;
        }
        return null;
    }

    //удаление рецепта:
    @Override
    public boolean deleteRecipe(long id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        }
        return false;
    }

    //получение всех рецептов:
    @Override
    public Map<Long, Recipe> getAllRecipes() {
        return recipeMap;
    }
}