package net.recipes.services.impl;

import net.recipes.model.Ingredient;
import net.recipes.model.Recipe;
import net.recipes.services.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Recipe recipe) {
        return recipe.getName() != null
                && recipe.getSteps() != null
                && !StringUtils.isEmpty(recipe.getName())
                && !recipe.getSteps().isEmpty()
                && recipe.getIngredients() != null
                && !recipe.getIngredients().isEmpty();
    }

    @Override
    public boolean validate(Ingredient ingredient) {
        return ingredient.getName() != null
                && !StringUtils.isEmpty(ingredient.getName())
                && ingredient.getTypeOfCount() != null;
    }
}