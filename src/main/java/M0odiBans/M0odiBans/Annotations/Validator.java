package M0odiBans.M0odiBans.Annotations;

import org.bukkit.command.CommandSender;

import java.lang.annotation.Annotation;

public interface Validator {

    /**
     * Метод для валидации данных для проверки возможности дальнейшего
     * ее исполнения.
     *
     * @param annotation Аннотация, данные из которой валидируем.
     * @param sender Отправитель команды.
     * @param args Аргументы команды.
     * @return Возможность дальнейшего выполнения команды.
     *         true - если да, false, если нет.
     */
    boolean validate(Annotation annotation, CommandSender sender, String[] args);

}
