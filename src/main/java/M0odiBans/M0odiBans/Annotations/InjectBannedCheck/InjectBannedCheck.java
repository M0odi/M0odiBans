package M0odiBans.M0odiBans.Annotations.InjectBannedCheck;

import M0odiBans.M0odiBans.Managers.MessageManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Использование этой аннотации над классом-наследником
 * AbstractCommand остановит выполнение команды, если игрок забанен
 * и нам необходимо, чтобы он был разбанен/забанен.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectBannedCheck {

    /**
     * Сообщение, которое будет отправлено исполнителю
     * в случае, если он хочет заблокировать уже заблокированного игрока.
     */
    MessageManager alreadyBannedMessage() default MessageManager.ALREADY_BANNED;

    /**
     * Сообщение, которое будет отправлено исполнителю
     * в случае, если он хочет разблокировать незабаненного игрока.
     */
    MessageManager alreadyUnbannedMessage() default MessageManager.ALREADY_UNBANNED;

    /**
     * Если нам важно, чтобы игрок был заблокирован.
     */
    boolean importantBanned();

    /**
     * Если нам важно, чтобы игрок был разблокирован.
     */
    boolean importantUnbanned();

    /**
     * Позиция аргумента, в которой указан ник, на который
     * выдается наказание.
     */
    int argPosition() default 0;

}
