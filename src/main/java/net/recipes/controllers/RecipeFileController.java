package net.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.recipes.services.FileRecipeService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/recipes")
@Tag(name = "Файлы", description = "Скачать и закачать файлы с рецептами")
public class RecipeFileController {

    private final FileRecipeService recipeService;

    public RecipeFileController(FileRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(
            summary = "Скачать рецепты",
            description = "Файл скачается в формате .json"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл успешно скачался."
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Файл для скачивания не существует"
                    )
            }
    )
    @GetMapping(value = "/export/")
    public ResponseEntity<InputStreamResource> downloadDataFile() {
        try {
            File file = recipeService.getDataFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream((file)));

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.json\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(
            summary = "Закачать файл на сервер",
            description = "Подходят только файлы формата .json с рецептами"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "База рецептов успешно обновлена"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Файл не принят на сервер, либо ошибка сервера. Обратитесь к администратору"
            )
    })
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        recipeService.cleanDataFile();
        File dataFile = recipeService.getDataFile();

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}