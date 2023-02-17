package net.recipes.services.impl;

import lombok.RequiredArgsConstructor;
import net.recipes.exception.ValidationException;
import net.recipes.model.Ingredient;
import net.recipes.services.IngredientService;
import net.recipes.services.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private static long id = 1;
    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private final ValidationService validationService;
    private String ingredientsFilePath;



    //добавление ингредиентов в мапу:
    @Override
    public Long addIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredientMap.put(id, ingredient);
        return id++;
    }

    //получение игредиента по айди:
    @Override
    public Ingredient getIngredient(Long id) {
        if (!ingredientMap.containsKey(id)) {
            return null;
        }
        return ingredientMap.get(id);
    }

    //редактирование ингредиента:
    @Override
    public Ingredient editIngredient(Long id, Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        return ingredientMap.replace(id, ingredient);
    }

    //удаление ингредиента:
    @Override
    public boolean deleteIngredient(Long id) {
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

    @PostConstruct
    private void init() {
    }
}