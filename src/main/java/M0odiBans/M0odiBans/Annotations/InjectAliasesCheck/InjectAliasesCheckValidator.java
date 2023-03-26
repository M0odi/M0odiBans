package M0odiBans.M0odiBans.Annotations.InjectAliasesCheck;

import M0odiBans.M0odiBans.Annotations.Validator;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import org.bukkit.command.CommandSender;

import java.lang.annotation.Annotation;

public class InjectAliasesCheckValidator implements Validator {

    @Override @SuppressWarnings("ConstantConditions")
    public boolean validate(Annotation annotation, CommandSender sender, String[] args) {

        //... Получаем необходимые данные...
        InjectAliasesCheck injectAliasesCheck = (InjectAliasesCheck) annotation;

        Reason reason = Reason.getReasonFromConfig(args[injectAliasesCheck.argPosition()]);
        String punishment = injectAliasesCheck.punishment();
        String message = injectAliasesCheck.message().getMessage();

        //... Если данного наказания нет в списке поддерживаемых наказаний этого правила,
        // отменяем исполнение...
        if (!reason.getAliases().contains(punishment.toUpperCase())) {
            sender.sendMessage(message);
            return false;
        }

        //... Условие соблюдено, продолжаем выполнение команды...
        return true;

    }

}
