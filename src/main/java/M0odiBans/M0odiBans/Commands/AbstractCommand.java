package M0odiBans.M0odiBans.Commands;

import M0odiBans.M0odiBans.Annotations.Annotations;
import M0odiBans.M0odiBans.Annotations.Validator;
import M0odiBans.M0odiBans.M0odiBans;
import lombok.SneakyThrows;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

public abstract class AbstractCommand implements CommandExecutor {

    protected AbstractCommand(String commandName) {

        //... Регистрируем команду...
        PluginCommand command = M0odiBans.getInstance().getCommand(commandName);
        if (command == null) return;

        //... Присваиваем ей исполнителя...
        command.setExecutor(this);

    }

    @Override @SneakyThrows
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Class<? extends AbstractCommand> clazz = this.getClass();

        //... Валидируем аннотации на классе-наследнике...
        for (Annotation annotation : clazz.getAnnotations()) {

            //... Ищем валидатор этой аннотации...
            Class<? extends Validator> validator = Annotations.getValidatorAnnotation(annotation);

            //... Если его нет, пропускаем...
            if (validator == null) continue;

            //... Если валидатор есть, исполняем его метод...
            boolean result = (boolean) validator.getDeclaredMethod("validate", Annotation.class, CommandSender.class, String[].class)
                    .invoke(validator.getDeclaredConstructor().newInstance(), annotation, sender, args);

            //... Если валидатор выдал false, команда не может быть исполнена. Отменяем...
            if (!result) return true;

        }

        //... Проверяем команду на возможность исполнения, и, если она есть, выполняем...
        if (feasibilityCheck(sender, args)) executeCommand(sender, args);

        return true;

    }

    /**
     * Метод, в котором выполнятся вся основная логика команды.
     */
    protected abstract void executeCommand(CommandSender sender, String[] args);

    /**
     * Метод, в котором выполняется проверка на возможность исполнения команды.
     */
    protected abstract boolean feasibilityCheck(CommandSender sender, String[] args);

}
