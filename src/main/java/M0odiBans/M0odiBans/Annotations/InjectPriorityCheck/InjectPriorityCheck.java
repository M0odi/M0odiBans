package M0odiBans.M0odiBans.Annotations.InjectPriorityCheck;

import M0odiBans.M0odiBans.Managers.MessageManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Использование этой аннотации над классом-наследником
 * AbstractCommand остановит выполнение команды, если приоритет исполнителя команды
 * ниже приоритета игрока, которого он хочет наказать.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectPriorityCheck {

    /**
     * Сообщение, которое будет отправлено исполнителю команды,
     * если условие не будет выполнено.
     */
    MessageManager message() default MessageManager.YOU_LOW_PRIORITY;

    /**
     * Позиция аргумента, в котором находится игрок,
     * приоритет которого мы сравниваем.
     */
    int argPosition() default 0;

}
