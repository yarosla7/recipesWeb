package net.recipes.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Ingredient {
    private String name;
    private int count;
    private String typeOfCount;

    @Override
    public String toString() {
        return name + " - " + count + " " + typeOfCount;
    }
}