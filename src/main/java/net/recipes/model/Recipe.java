package net.recipes.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Recipe {

    private String name;
    private int timeTo;
    private List<Ingredient> ingredients;
    private List<String> steps;

    @Override
    public String toString() {
        return name + "\n Время приготовления: " + timeTo;
    }
}