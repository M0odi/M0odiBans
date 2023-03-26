package M0odiBans.M0odiBans.Annotations.InjectRequirements;

import M0odiBans.M0odiBans.Managers.MessageManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Использование этой аннотации над классом-наследником
 * AbstractCommand приводит к автоматическому валидированию
 * базовых требования для исполнения этой команды, таких как: <br>
 * - Проверка права на использование команды; <br>
 * - Проверка количества аргументов для использования команды. <br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectRequirements {

    /**
     * Сообщение, которое будет отправляться отправителю команды,
     * если у него нет прав на использование этой команды.
     */
    MessageManager notPermMessage() default MessageManager.NOT_PERMS;

    /**
     * Право, которым должен обладать отправитель, чтобы использовать команду.
     */
    String permission() default "M0odiBans.USE";

    /**
     * Минимальная длина массива аргументов, при которой
     * команда будет исполняться корректно.
     */
    int lengthUsage() default 2;

    /**
     * Сообщение, которое будет отправляться игроку,
     * если количество аргументов меньше минимально необходимого.
     */
    MessageManager usageMessage();

}
