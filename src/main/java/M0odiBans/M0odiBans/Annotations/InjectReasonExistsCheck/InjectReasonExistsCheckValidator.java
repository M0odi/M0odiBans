package M0odiBans.M0odiBans.Annotations.InjectReasonExistsCheck;

import M0odiBans.M0odiBans.Annotations.Validator;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import org.bukkit.command.CommandSender;

import java.lang.annotation.Annotation;

public class InjectReasonExistsCheckValidator implements Validator {

    @Override
    public boolean validate(Annotation annotation, CommandSender sender, String[] args) {

        //... Получаем необходимые данные...
        InjectReasonExistsCheck injectReasonExistsCheck = (InjectReasonExistsCheck) annotation;

        Reason reason = Reason.getReasonFromConfig(args[injectReasonExistsCheck.argPosition()]);
        String message = injectReasonExistsCheck.message().getMessage();

        //... Если данного правила не существует, отменяем исполнение команды...
        if (reason == null) {
            sender.sendMessage(message);
            return false;
        }

        //... Условие соблюдено, продолжаем выполнение команды...
        return true;

    }

}
