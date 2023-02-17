package net.recipes.services;

public interface FileIngredientService {
    boolean saveIngredientToFile(String json);

    String readFromIngredientFile();
}
