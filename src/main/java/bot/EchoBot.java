package bot;

import bot.command.*;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Map;

public class EchoBot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;

    private final Map<String, FileCommand> commands;


    public EchoBot(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
        this.commands = Map.of(
                "/file", new CustomFileCommand(telegramClient),
                "/pic", new PictureFileCommand(telegramClient),
                "/video", new VideoFileCommand(telegramClient),
                "/audio", new AudioFileCommand(telegramClient)
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
