package M0odiBans.M0odiBans.Annotations.InjectAliasesCheck;

import M0odiBans.M0odiBans.Managers.MessageManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Использование этой аннотации над классом-наследником
 * AbstractCommand остановит выполнение команды, если причина,
 * по которой выдается наказание, не поддерживает данное наказание.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectAliasesCheck {

    /**
     * Сообщение, которое будет отправлено исполнителю команды,
     * если условие не будет выполнено.
     */
    MessageManager message() default MessageManager.REASON_NOT_ALLOWED_FOR_THIS_PUNISHMENT;

    /**
     * Наказание, которое будет проверяться.
     */
    String punishment();

    /**
     * Позиция аргумента в args, где находится причина наказания.
     */
    int argPosition() default 1;

}
