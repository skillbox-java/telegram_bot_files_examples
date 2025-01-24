package bot.command;

import bot.FileCache;
import files.ResourceFileReader;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class CustomFileCommand extends FileCommand {

    public static final String FILE_PATH = "favourite_songs.txt";

    public CustomFileCommand(TelegramClient client, FileCache fileCache) {
        super(client, fileCache);
    }

    @Override
    public void handle(Update update) {
        String cacheKey = generateCacheKey(FILE_PATH);
        String cachedFileId = fileCache.getFileId(cacheKey);

        long startTime = System.currentTimeMillis();

        if (cachedFileId != null) {
            sendCachedDocument(update, cachedFileId);

        } else {
            // Если нет, отправляем файл и сохраняем file_id
            sendNewDocument(update, cacheKey);
        }
        long finishTime = System.currentTimeMillis();

        System.out.printf("Отправка файла заняла %d мс%n", finishTime - startTime);
    }

    private void sendCachedDocument(Update update, String cachedFileId) {
        SendDocument document = SendDocument.builder()
                .chatId(update.getMessage().getChatId())
                .document(new InputFile(cachedFileId)) // Используем file_id
                .caption("Список любимых песен (из кэша)")
                .build();

        try {
            client.execute(document);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Ошибка отправки из кэша", e);
        }
    }

    private void sendNewDocument(Update update, String cacheKey) {
        InputFile file = ResourceFileReader.getInputFileFromResources(FILE_PATH);

        SendDocument document = SendDocument.builder()
                .chatId(update.getMessage().getChatId())
                .document(file)
                .caption("Список любимых песен")
                .build();

        try {
            Message response = client.execute(document);
            // Сохраняем file_id в кэш
            String newFileId = response.getDocument().getFileId();
            fileCache.saveFileId(cacheKey, newFileId);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Ошибка отправки файла", e);
        }
    }
}
