package config;

public record Config(
        String botApiToken
) {
    public Config {
        if (botApiToken == null || botApiToken.isEmpty()) {
            throw new RuntimeException("Токен не задан!");
        }
    }
}
