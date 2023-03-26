package M0odiBans.M0odiBans.Annotations.InjectReasonExistsCheck;

import M0odiBans.M0odiBans.Managers.MessageManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Использование этой аннотации над классом-наследником
 * AbstractCommand остановит выполнение команды, если причины,
 * по которой выдается наказание, не существует.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectReasonExistsCheck {

    /**
     * Сообщение, которое будет отправлено исполнителю команды,
     * если условие не будет выполнено.
     */
    MessageManager message() default MessageManager.REASON_NOT_EXISTS;

    /**
     * Позиция аргумента для валидации в args.
     */
    int argPosition() default 1;

}
