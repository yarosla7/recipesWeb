package net.recipes.services.impl;

import net.recipes.model.Ingredient;
import net.recipes.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private static long id = 0;

    //добавление ингредиентов в мапу:
    @Override
    public long addIngredient(Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
        return id++;
    }

    //получение игредиента по айди:
    @Override
    public Ingredient getIngredient(long id) {
        return ingredientMap.get(id);
    }

    //редактирование ингредиента:
    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            return ingredient;
        }
        return null;
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