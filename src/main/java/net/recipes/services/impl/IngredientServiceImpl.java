package net.recipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.recipes.exception.ValidationException;
import net.recipes.model.Ingredient;
import net.recipes.services.IngredientService;
import net.recipes.services.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static long id = 1;
    private Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private final ValidationService validationService;
    final private FileIngredientServiceImpl fileIngredientService;

    public IngredientServiceImpl(ValidationService validationService, FileIngredientServiceImpl fileIngredientService) {
        this.validationService = validationService;
        this.fileIngredientService = fileIngredientService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    //добавление ингредиентов в мапу:
    @Override
    public Long addIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredientMap.put(id, ingredient);
        saveToFile();
        return id++;
    }

    //получение ингредиента по айди:
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
        ingredientMap.replace(id, ingredient);
        saveToFile();
        return ingredientMap.get(id);
    }

    //удаление ингредиента:
    @Override
    public boolean deleteIngredient(Long id) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }

    //получение всех ингредиентов:
    @Override
    public Map<Long, Ingredient> getAllIngredient() {
        return ingredientMap;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileIngredientService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = fileIngredientService.readFromFile();
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}