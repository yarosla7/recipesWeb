package net.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.recipes.model.Recipe;
import net.recipes.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты",
        description = "CRUD operations and other endpoints for work with.")
public class RecipesController {
    private final RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(
            summary = "Добавление нового рецепта",
            description = "Сохраняет рецепт в список рецептов типа ключ-значение")
    @ApiResponse(
            responseCode = "200",
            description = "Рецепт создан и добавлен в карту")
    public ResponseEntity<Long> addRecipe(@RequestBody Recipe recipe) {
        long id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск по id рецепта",
            description = "Можно искать по айди"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не найден"
                    )

            })
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование рецепта",
            description = "Принимает id рецепта, ищет его в карте и затирает на новые значения по тому же id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден и изменен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден"
            )
    })
    public ResponseEntity<Recipe> editRecipeById(@PathVariable Long id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipe(id, recipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта по id",
            description = "Принимает id рецепта, ищет его в карте и удаляет из неё")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден и удален",
                    content =
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден"
            )
    })
    public ResponseEntity<Void> deleteRecipeById(@PathVariable Long id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Получение всех рецептов", description = "Возвращает карту, с хранящимися в ней рецептами")
    @ApiResponse(
            responseCode = "200",
            description = "Карта рецептов получена",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
            }
    )
    public ResponseEntity<Map<Long, Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }
}