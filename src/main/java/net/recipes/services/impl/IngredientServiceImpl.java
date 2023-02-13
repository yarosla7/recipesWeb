package net.recipes.services.impl;

import net.recipes.exception.ValidationException;
import net.recipes.model.Ingredient;
import net.recipes.services.IngredientService;
import net.recipes.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private static long id = 1;
    private final ValidationService validationService;

    public IngredientServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    //добавление ингредиентов в мапу:
    @Override
    public long addIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredientMap.put(id, ingredient);
        return id++;
    }

    //получение игредиента по айди:
    @Override
    public Optional<Ingredient> getIngredient(long id) {
        return Optional.ofNullable(ingredientMap.get(id));
    } // про Optional рассказал Георгий

    //редактирование ингредиента:
    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        return ingredientMap.replace(id, ingredient);
    }

    //удаление ингредиента:
    @Override
    public boolean deleteIngredient(long id) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            return true;
        }
        return false;
    }

    //получение всех ингредиентов:
    @Override
    public Map<Long, Ingredient> getAllIngredient() {
        return ingredientMap;
    }
}