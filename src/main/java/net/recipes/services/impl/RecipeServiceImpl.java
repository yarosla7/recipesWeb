package net.recipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.recipes.exception.ValidationException;
import net.recipes.model.Ingredient;
import net.recipes.model.Recipe;
import net.recipes.services.FileRecipeService;
import net.recipes.services.RecipeService;
import net.recipes.services.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    final private FileRecipeService fileRecipeService;
    private final ValidationService validationService;
    private static long id = 1;
    private Map<Long, Recipe> recipeMap = new HashMap<>();

    public RecipeServiceImpl(FileRecipeService fileRecipeService, ValidationService validationService) {
        this.fileRecipeService = fileRecipeService;
        this.validationService = validationService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    //добавление рецепта в мапу рецептов:
    @Override
    public Long addRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipeMap.put(id, recipe);
        saveToFile();
        return id++;
    }

    //получение рецепта по айди:
    @Override
    public Recipe getRecipe(Long id) {
        if (!recipeMap.containsKey(id)) {
            return null;
        }
        return recipeMap.get(id);
    }

    //редактирование рецепта:
    @Override
    public Recipe editRecipe(Long id, Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipeMap.replace(id, recipe);
        saveToFile();
        return recipeMap.get(id);
    }

    //удаление рецепта:
    @Override
    public boolean deleteRecipe(Long id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }

    //получение всех рецептов:
    @Override
    public Map<Long, Recipe> getAllRecipes() {
        return recipeMap;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileRecipeService.saveToMap(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = fileRecipeService.readFromFile();
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File recipesToTxt() throws IOException {
        return fileRecipeService.saveToFile(recipesToString(), fileRecipeService.returnPath()).toFile();
    }

    private String recipesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        String dot = "\t• ";
        for (Recipe recipe : recipeMap.values()) {
            stringBuilder.append("\n").append(recipe.toString()).append("\n");
            stringBuilder.append("\nИнгредиенты:\n");

            for (Ingredient ingredient : recipe.getIngredients()) {
                stringBuilder.append(dot).append(ingredient.toString()).append("\n");
            }
            stringBuilder.append("\n  Инструкция по приготовлению:");

            for (String step : recipe.getSteps()) {
                stringBuilder.append(dot).append(step).append("\n");
            }
        }
        return stringBuilder.append("\n").toString();
    }
}