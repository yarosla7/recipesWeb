package net.recipes.services.impl;

import lombok.Value;
import net.recipes.services.FileIngredientService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service

public class FileIngredientServiceImpl implements FileIngredientService {

    @Value("${name.to.data.file}")
    private String nameDataFile;
    @Value("${path.to.data.file}")
    private String pathDataFile;

    @Override
    public boolean saveIngredientToFile(String json) {
        try {
            Files.writeString(Path.of(pathDataFile, nameDataFile), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromIngredientFile() {
        try {
            return Files.readString(Path.of(pathDataFile, nameDataFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
