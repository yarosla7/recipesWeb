package net.recipesWeb.services.impl;

import net.recipesWeb.model.Ingredient;
import net.recipesWeb.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientServiceImpl implements IngredientService {

    public int id;
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    //добавление ингредиентов в мапу:
    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
    }
    //получение игредиента по айди:
    @Override
    public Ingredient getIngredient(int id) {
        return ingredientMap.get(id);
    }
}