package bot.command;

import files.ResourceFileReader;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class AudioFileCommand extends FileCommand {
    public AudioFileCommand(TelegramClient client) {
        super(client);
    }

    @Override
    public void handle(Update update) {
        InputFile file = ResourceFileReader.getInputFileFromResources("audio.mp3");

        SendAudio sendAudio = SendAudio.builder()
                .chatId(update.getMessage().getChatId())
                .audio(file)
                .caption("Послушай этот трек!")
                .build();

        sendAudio.setTitle("Hope"); // Название аудио
        sendAudio.setPerformer("Hugo Dujardin"); // Исполнитель

        try {
            client.execute(sendAudio);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
