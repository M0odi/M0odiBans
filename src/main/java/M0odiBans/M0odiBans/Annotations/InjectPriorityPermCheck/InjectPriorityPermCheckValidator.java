package M0odiBans.M0odiBans.Annotations.InjectPriorityPermCheck;

import M0odiBans.M0odiBans.Annotations.Validator;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.Annotation;

public class InjectPriorityPermCheckValidator implements Validator {

    @Override
    public boolean validate(Annotation annotation, CommandSender sender, String[] args) {

        //... Получаем необходимые данные...
        InjectPriorityPermCheck injectPriorityPermCheck = (InjectPriorityPermCheck) annotation;

        int argPosition = injectPriorityPermCheck.argPosition();
        String nicknameTarget = args[argPosition];
        String message = injectPriorityPermCheck.message().getMessage();

        try {

            //... Берем игрока по UUID, которое берем из LuckPerms...
            // В случае ошибки - продолжаем исполнение команды...
            Player player = Bukkit.getPlayer
                    (LuckPermsProvider.get().getUserManager().getUser(nicknameTarget).getUniqueId());

            //... Если у игрока есть приоритет от наказаний, отменяем исполнение команды...
            if (player != null && player.hasPermission("M0odiBans.PRIORITY")) {
                sender.sendMessage(message);
                return false;
            }

        } catch (NullPointerException ignored) {}

        //... У игрока нет приоритета, продолжаем исполнение команды...
        return true;

    }

}
