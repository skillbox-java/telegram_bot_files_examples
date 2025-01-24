package bot.command;

import bot.FileCache;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public abstract class FileCommand {
    protected final TelegramClient client;
    protected final FileCache fileCache;

    public FileCommand(TelegramClient client, FileCache fileCache) {
        this.client = client;
        this.fileCache = fileCache;
    }

    abstract public void handle(Update update);

    // Метод для генерации ключа кэша (на основе пути к файлу), можно добавить дополнительную логику
    protected String generateCacheKey(String filePath) {
        return filePath;
    }
}
