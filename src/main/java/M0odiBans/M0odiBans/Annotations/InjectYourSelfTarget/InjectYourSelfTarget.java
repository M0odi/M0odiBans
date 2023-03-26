package M0odiBans.M0odiBans.Annotations.InjectYourSelfTarget;

import M0odiBans.M0odiBans.Managers.MessageManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Использование этой аннотации над классом-наследником
 * AbstractCommand запретит отправителю команды использовать
 * данную команду на себя.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectYourSelfTarget {

    /**
     * Сообщение, которое будет отправлено исполнителю команды,
     * если условие не будет выполнено.
     */
    MessageManager message() default MessageManager.YOUR_SELF_USAGE;

    /**
     * Позиция аргумента для валидации в args.
     */
    int argPosition() default 0;

}
