package M0odiBans.M0odiBans.Managers;

import M0odiBans.M0odiBans.M0odiBans;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public final class ConfigManager {

    private final File file;

    @Getter
    private final FileConfiguration config;

    /**
     * Создает файл с указанным названием в папке плагина.
     * Используется для хранения каких-либо ресурсов,
     * инициализация которых требуется на моменте загрузки плагина.
     *
     * @param name Название файла.
     *             Необходимо указание расширения.
     *             Пример: messages.yml
     */
    public ConfigManager(String name) {
        file = new File(M0odiBans.getInstance().getDataFolder(), name);

        // Если файла не существует, сохраняем как ресурс...
        if (!file.exists()) M0odiBans.getInstance().saveResource(name, false);

        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Метод для сохранения файла.
     */
    @SneakyThrows
    public void save() {
        config.save(file);
    }

}
