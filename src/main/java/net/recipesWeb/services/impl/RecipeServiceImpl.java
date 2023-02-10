package net.recipesWeb.services.impl;

import net.recipesWeb.model.Recipe;
import net.recipesWeb.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiceImpl implements RecipeService {

    public int id;
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();
    //добавление рецепта в мапу рецептов:
    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
    }
    //получение рецепта по айди:
    @Override
    public Recipe getRecipe(int id) {
        return recipeMap.get(id);
    }
}
