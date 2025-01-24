package bot.command;

import bot.FileCache;
import files.ResourceFileReader;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class VideoFileCommand extends FileCommand {
    public VideoFileCommand(TelegramClient client, FileCache fileCache) {
        super(client, fileCache);
    }

    @Override
    public void handle(Update update) {
        InputFile file = ResourceFileReader.getInputFileFromResources("cat.mp4");

        SendVideo sendVideo = SendVideo.builder()
                .chatId(update.getMessage().getChatId())
                .video(file)
                .caption("Мой помощник!")
                .build();

        try {
            client.execute(sendVideo);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
