package bot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileCache {
    private final Map<String, String> fileIdCache = new ConcurrentHashMap<>();

    // Сохраняем file_id по ключу (например, имя файла или хеш)
    public void saveFileId(String key, String fileId) {
        fileIdCache.put(key, fileId);
    }

    // Получаем file_id из кэша
    public String getFileId(String key) {
        return fileIdCache.get(key);
    }
}
