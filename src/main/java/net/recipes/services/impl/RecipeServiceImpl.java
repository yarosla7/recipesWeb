package net.recipes.services.impl;

import net.recipes.exception.ValidationException;
import net.recipes.model.Recipe;
import net.recipes.services.RecipeService;
import net.recipes.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final Map<Long, Recipe> recipeMap = new HashMap<>();
    private static long id = 1;
    private final ValidationService validationService;

    public RecipeServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    //добавление рецепта в мапу рецептов:
    @Override
    public long addRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipeMap.put(id++, recipe);
        return id++;
    }

    //получение рецепта по айди:
    @Override
    public Optional<Recipe> getRecipe(long id) {
        return Optional.ofNullable(recipeMap.get(id));
    }

    //редактирование рецепта:
    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        return recipeMap.replace(id, recipe);
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