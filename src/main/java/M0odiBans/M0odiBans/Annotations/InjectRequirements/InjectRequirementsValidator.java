package M0odiBans.M0odiBans.Annotations.InjectRequirements;

import M0odiBans.M0odiBans.Annotations.Validator;
import M0odiBans.M0odiBans.Managers.MessageManager;
import org.bukkit.command.CommandSender;

import java.lang.annotation.Annotation;

public class InjectRequirementsValidator implements Validator {

    @Override
    public boolean validate(Annotation annotation, CommandSender sender, String[] args) {

        //... Получаем необходимые данные для дальнейшей обработки...
        InjectRequirements injectRequirements = (InjectRequirements) annotation;

        String permission = injectRequirements.permission();
        MessageManager notPermMessage = injectRequirements.notPermMessage();
        int usageLength = injectRequirements.lengthUsage();
        MessageManager usageMessage = injectRequirements.usageMessage();

        //... Если у отправителя нет прав для использования этой команды,
        // отменяем исполнение...
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(notPermMessage.getMessage());
            return false;
        }

        //... Если количество аргументов меньше минимально необходимого
        // для использования этой команды, отменяем исполнение...
        if (args.length < usageLength) {
            sender.sendMessage(usageMessage.getMessage());
            return false;
        }

        //... Все требования соблюдены, продолжаем исполнение...
        return true;

    }
}
