package bot.command;

import files.ResourceFileReader;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class CustomFileCommand extends FileCommand {
    public CustomFileCommand(TelegramClient client) {
        super(client);
    }

    @Override
    public void handle(Update update) {
        InputFile file = ResourceFileReader.getInputFileFromResources("favourite_songs.txt");

        SendDocument document = SendDocument.builder()
                .chatId(update.getMessage().getChatId())
                .document(file)
                .caption("Cписок любимых песен")
                .build();

        try {
            client.execute(document);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
