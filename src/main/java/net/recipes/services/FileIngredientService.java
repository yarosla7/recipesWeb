package net.recipes.services;
public interface FileIngredientService {
    /**
     * Сохранение объекта в файл
     * @param json - формат файла
     * @return - возвращает true, если объект сохранен в файл
     */
    boolean saveToFile(String json);

    /**
     * чтение из файла и добавление в мапу
     * @return - возвращает строку
     */
    String readFromFile();

    /**
     * удаление файла
     * @return - возвращает true, если файл пустой
     */
}
