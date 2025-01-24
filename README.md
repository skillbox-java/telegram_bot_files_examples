# Пример работы с файлами в Telegram API

В боте реализовано несколько команд для отправки
пользователю разных типов файлов:

- `/file` - отправить текстовый файл
- `/pic` - отправить изображения
- `/video` - отправить видео
- `/audio` - отправить аудиотрек

Для работа с серверами телеграм используется библиотека
[rubenlagus/TelegramBots](https://github.com/rubenlagus/TelegramBots)

## Установка Bot Token

Для передачи в приложение значение токена используйте переменную окружения.

Установить переменную с токеном для текущей сессии:

- Linux/macOS

```bash
export BOT_TOKEN=123
```

- Windows (PowerShell)

```powershell
$env:BOT_TOKEN = "123"
```

- Среда разработки Intellij IDEA

В настройках запуска приложения включите возможность задать переменные среды.
Modify Options → Env Variables. В поле **Environment variables** введите
`BOT_TOKEN=123`. Сохраните конфигурацию.

## Источники медиа файлов

1. `src/main/resources/cat.mp4` https://www.videvo.net/video/cat-with-blue-eyes/1561387/#rs=video-box
1. `src/main/resources/audio.mp3`
Music by Bensound.com/free-music-for-videos
License code: B1CIABNAAWIZZXG8
1. `src/main/resources/cat.jpg` Сгенерировано с помощью ИИ GigaChat