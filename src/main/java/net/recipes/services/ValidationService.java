package net.recipes.services;

import net.recipes.model.Ingredient;
import net.recipes.model.Recipe;

public interface ValidationService {
    boolean validate(Recipe recipe);

    boolean validate(Ingredient ingredient);
}