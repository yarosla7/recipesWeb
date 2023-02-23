package net.recipes.services;

import net.recipes.model.Ingredient;

import java.util.Map;

public interface IngredientService {

    Long addIngredient(Ingredient ingredient);

    Ingredient getIngredient(Long id);

    Ingredient editIngredient(Long id, Ingredient ingredient);

    boolean deleteIngredient(Long id);

    Map<Long, Ingredient> getAllIngredient();
}