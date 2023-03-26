package M0odiBans.M0odiBans.Objects.PunishmentComponents;

import M0odiBans.M0odiBans.M0odiBans;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

@Data @AllArgsConstructor
public class Reason {

    private final String reason; // Название правила. Например, 1.1, 1.2, 1.3 и так далее.

    private final String description; // Описание правила.

    private final List<String> aliases; // Разрешенные к применению наказания для этого правила.

    private final long time; // Время, на которое может применяться данное наказание.
                             // Если время не указано, то - 0.


    /**
     * @param reason Причина. Например - 1.1, 1.2, 1.3 и так далее.
     * @return Объект причины.
     */
    public static Reason getReasonFromConfig(String reason) {

        //... Получение секции из файла конфигурации, хранящей данное правило...
        ConfigurationSection reasonSection =
                M0odiBans.getReasons().getConfig().getConfigurationSection(
                        "REASONS" + "." + reason.replaceAll("\\.", "|"));

        //... Если данной секции не существует - такого правила не существует,
        // возвращаем null...
        if (reasonSection == null) return null;

        //... Получаем необходимые данные из файла конфигурации...
        String description = reasonSection.getString("DESCRIPTION");
        List<String> aliases = reasonSection.getStringList("ALIASES");
        long time = TimeUnit.getFinalPunishmentTime
                (reasonSection.getString("TIME-UNIT"),
                        reasonSection.getLong("TIME"));

        //... Собираем объект правила и возвращаем его...
        return new Reason(reason, description, aliases, time);

    }

}
