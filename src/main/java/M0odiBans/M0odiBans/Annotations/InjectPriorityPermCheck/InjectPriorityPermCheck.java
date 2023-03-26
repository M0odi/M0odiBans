package M0odiBans.M0odiBans.Annotations.InjectPriorityPermCheck;

import M0odiBans.M0odiBans.Managers.MessageManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Использование этой аннотации над классом-наследником
 * AbstractCommand остановит выполнение команды, если у игрока,
 * на которого отправляется наказание, есть право M0odiBans.PRIORITY
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectPriorityPermCheck {

    /**
     * Сообщение, которое будет отправлено исполнителю команды,
     * если условие не будет выполнено.
     */
    MessageManager message() default MessageManager.IS_PRIORITY_PLAYER;

    /**
     * Позиция аргумента, в котором находится игрок,
     * у которого мы проверяем наличие защиты от наказаний.
     */
    int argPosition() default 0;

}
