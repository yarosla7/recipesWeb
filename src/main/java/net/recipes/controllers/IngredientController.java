package net.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.recipes.model.Ingredient;
import net.recipes.model.Recipe;
import net.recipes.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "Из них состоят рецепты")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "Добавляет ингредиенты в карту",
            description = "Нужно написать название ингредиента, количество и тип этого количества. Например: штук, грамм, миллилитры."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент создан и добавлен в карту"
    )
    public ResponseEntity<Long> addIngredient(@RequestBody Ingredient ingredient) {
        long id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск по id ингредиента",
            description = "Можно искать по айди"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ингридиет найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиент не найден"
            )
    })
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента по id",
            description = "Принимает id ингредиента, ищет его в карте и затирает на новые значения по тому же id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиет найден и изменен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиент не найден"
            )
    })
    public ResponseEntity<Ingredient> editIngredientById(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient1);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента по id",
            description = "Принимает id ингредиента, ищет его в карте и удаляет из неё")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингридиет найден и удален",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не найден"
                    )
            })
    public ResponseEntity<Void> deleteIngredientById(@PathVariable Long id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(
            summary = "Получение всех ингредиентов",
            description = "Возвращает карту, с хранящимися в ней игредиентами")
    @ApiResponse(
            responseCode = "200",
            description = "Карта ингредиентов получена",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
            }
    )
    public ResponseEntity<Map<Long, Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredient());
    }
}