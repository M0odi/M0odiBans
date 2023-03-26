package M0odiBans.M0odiBans.Annotations.InjectPriorityCheck;

import M0odiBans.M0odiBans.Annotations.Validator;
import M0odiBans.M0odiBans.M0odiBans;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.command.CommandSender;

import java.lang.annotation.Annotation;

public class InjectPriorityCheckValidator implements Validator {

    @Override
    public boolean validate(Annotation annotation, CommandSender sender, String[] args) {

        //... Получаем необходимые данные...
        InjectPriorityCheck injectPriorityCheck = (InjectPriorityCheck) annotation;

        int argPosition = injectPriorityCheck.argPosition();
        String nicknameTarget = args[argPosition];
        String message = injectPriorityCheck.message().getMessage();

        int prioritySender;
        int priorityTarget;

        //... Получаем приоритеты отправителя команды и игрока, на которого направлено наказание.
        // В случае какой-либо ошибки, продолжаем исполнять команду.
        try {

            prioritySender =
                    M0odiBans.getInstance().getConfig().getInt("priorities." +
                            LuckPermsProvider.get().getUserManager().getUser(sender.getName()).getPrimaryGroup());

            priorityTarget =
                    M0odiBans.getInstance().getConfig().getInt("priorities." +
                            LuckPermsProvider.get().getUserManager().getUser(nicknameTarget).getPrimaryGroup());

        } catch (NullPointerException ex) {
            return true;
        }

        //... Если приоритет отправителя меньше, отменяем исполнение команды...
        if (prioritySender < priorityTarget) {
            sender.sendMessage(message);
            return false;
        }

        //... Проверка соблюдена, продолжаем исполнение команды...
        return true;

    }

}
