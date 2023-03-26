package M0odiBans.M0odiBans.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.ChatColor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class LogUtils {

    public static String prefix = ChatColor.GRAY + "[" +
            ChatColor.AQUA + "M0odi" + ChatColor.RED + "Bans" + ChatColor.GRAY + "]";

    public static void printTitleInConsole() {
        System.out.println("\n" +
                ChatColor.AQUA + "███╗░░░███╗░█████╗░░█████╗░██████╗░██╗" + ChatColor.RED + "██████╗░░█████╗░███╗░░██╗░██████╗\n" +
                ChatColor.AQUA + "████╗░████║██╔══██╗██╔══██╗██╔══██╗██║" + ChatColor.RED + "██╔══██╗██╔══██╗████╗░██║██╔════╝\n" +
                ChatColor.AQUA + "██╔████╔██║██║░░██║██║░░██║██║░░██║██║" + ChatColor.RED + "██████╦╝███████║██╔██╗██║╚█████╗░\n" +
                ChatColor.AQUA + "██║╚██╔╝██║██║░░██║██║░░██║██║░░██║██║" + ChatColor.RED + "██╔══██╗██╔══██║██║╚████║░╚═══██╗\n" +
                ChatColor.AQUA + "██║░╚═╝░██║╚█████╔╝╚█████╔╝██████╔╝██║" + ChatColor.RED + "██████╦██║░░██║██║░╚███║██████╔╝\n" +
                ChatColor.AQUA + "╚═╝░░░░░╚═╝░╚════╝░░╚════╝░╚═════╝░╚═╝" + ChatColor.RED + "╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░");
        System.out.println(" ");
    }

    public static void printInfoAboutPluginInConsole() {
        System.out.println(ChatColor.AQUA + "|" + ChatColor.GRAY + "Плагин разработан Денисом Тимофеевым");
        System.out.println(ChatColor.AQUA + "|" + ChatColor.GRAY + "Вконтакте: §chttps://vk.com/m0odi");
        System.out.println(ChatColor.AQUA + "| " + ChatColor.GRAY + "Telegram: §chttps://t.me/M0odiX");
        System.out.println(ChatColor.AQUA + "| ");
        System.out.println(ChatColor.AQUA + "|" + ChatColor.GRAY + "Проверенные версии плагина: §c1.12 - 1.19");
        System.out.println(" ");
    }



}
