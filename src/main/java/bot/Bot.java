package bot;

import bot.command.*;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Map;

public class Bot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;

    private final Map<String, FileCommand> commands;

    private final FileCache fileCache;


    public Bot(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
        this.fileCache = new FileCache();
        this.commands = Map.of(
                "/file", new CustomFileCommand(telegramClient, fileCache),
                "/pic", new PictureFileCommand(telegramClient, fileCache),
                "/video", new VideoFileCommand(telegramClient, fileCache),
                "/audio", new AudioFileCommand(telegramClient, fileCache)
                );
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            System.out.println("There is no any text in Update" + update.getUpdateId());
            return;
        }

        String messageText = update.getMessage().getText().strip();
        long chatId = update.getMessage().getChatId();

        FileCommand fileCommand = commands.get(messageText);

        if (fileCommand != null) {
            fileCommand.handle(update);
            return;
        }

        System.out.println(messageText + "from chatId=" + chatId);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(messageText)
                .build();

        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
