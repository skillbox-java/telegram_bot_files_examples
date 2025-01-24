package bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public abstract class FileCommand {
    protected final TelegramClient client;

    public FileCommand(TelegramClient client) {
        this.client = client;
    }

    abstract public void handle(Update update);
}
