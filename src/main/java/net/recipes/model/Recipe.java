package net.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class Recipe {

    private final String name;
    private final int timeTo;
    private final List<Ingredient> ingredients;
    private final List<String> steps;
}
