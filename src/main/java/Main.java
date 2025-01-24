import bot.EchoBot;
import config.ConfigReaderEnvironment;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class Main {
    public static void main(String[] args) {
        var config = new ConfigReaderEnvironment().read();
        var botToken = config.botApiToken();

        try (var botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(botToken,
                    new EchoBot(new OkHttpTelegramClient(botToken)));

            System.out.println("Bot is running!");

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
