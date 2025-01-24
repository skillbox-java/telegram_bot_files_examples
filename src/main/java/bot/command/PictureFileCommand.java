package bot.command;

import bot.FileCache;
import files.ResourceFileReader;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class PictureFileCommand extends FileCommand {
    public PictureFileCommand(TelegramClient client, FileCache fileCache) {
        super(client, fileCache);
    }

    @Override
    public void handle(Update update) {
        InputFile file = ResourceFileReader.getInputFileFromResources("cat.jpg");

        SendPhoto photo = SendPhoto.builder()
                .chatId(update.getMessage().getChatId())
                .photo(file)
                .caption("Мой помощник!")
                .build();

        try {
            client.execute(photo);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
