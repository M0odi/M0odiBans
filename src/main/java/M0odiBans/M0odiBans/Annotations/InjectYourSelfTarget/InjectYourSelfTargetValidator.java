package M0odiBans.M0odiBans.Annotations.InjectYourSelfTarget;

import M0odiBans.M0odiBans.Annotations.Validator;
import org.bukkit.command.CommandSender;

import java.lang.annotation.Annotation;

public class InjectYourSelfTargetValidator implements Validator {

    @Override
    public boolean validate(Annotation annotation, CommandSender sender, String[] args) {

        //... Получаем необходимые данные для работы...
        InjectYourSelfTarget injectYourSelfTarget = (InjectYourSelfTarget) annotation;

        String target = args[injectYourSelfTarget.argPosition()];
        String message = injectYourSelfTarget.message().getMessage();

        //... Если данный аргумент эквивалентен никнейму отправителя,
        // отменяем исполнение команды...
        if (sender.getName().equalsIgnoreCase(target)) {
            sender.sendMessage(message);
            return false;
        }

        //... Условие соблюдено, продолжаем выполнение команды...
        return true;

    }

}
