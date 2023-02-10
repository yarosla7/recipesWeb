package net.recipesWeb.services;

import net.recipesWeb.model.Ingredient;

public interface IngredientService {

    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);
}
