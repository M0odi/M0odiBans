package M0odiBans.M0odiBans.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;

import java.util.List;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class ChatUtils {

    public static String convertColorCodes(String message) {
        return message.replaceAll("&", "§");
    }

    public static String fromListToString(List<String> message, String regex) {

        StringBuilder sb = new StringBuilder();

        for (String s : message) sb.append(s).append(regex);

        return sb.toString();

    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(message);
    }

}
