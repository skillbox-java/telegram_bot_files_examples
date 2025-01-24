package files;

import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.InputStream;

public class ResourceFileReader {
    public static InputFile getInputFileFromResources(String resourceFileName) {
        // Получение ClassLoader
        ClassLoader classLoader = ResourceFileReader.class.getClassLoader();

        // Получение InputStream для файла из ресурсов
        InputStream resourceInputStream = classLoader.getResourceAsStream(resourceFileName);

        if (resourceInputStream == null) {
            throw new IllegalArgumentException("Файл не найден в ресурсе: " + resourceFileName);
        }

        // Создание InputFile из InputStream
        return new InputFile(resourceInputStream, resourceFileName);
    }

}
