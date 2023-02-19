package net.recipes.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface FileRecipeService {
    /**
     * Сохранение объекта в файл
     *
     * @param json - формат файла
     * @return - возвращает true, если объект сохранен в файл
     */
    boolean saveToMap(String json);

    /**
     * чтение из файла и добавление в мапу
     *
     * @return - возвращает строку
     */
    String readFromFile();

    /**
     * получение файла
     *
     * @return файл
     */
    File getDataFile();

    boolean cleanDataFile();

    Path returnPath();

    Path saveToFile(String content, Path path) throws IOException;
}