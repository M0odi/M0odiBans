package M0odiBans.M0odiBans.Annotations.InjectMutedCheck;

import M0odiBans.M0odiBans.Managers.MessageManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Использование этой аннотации над классом-наследником
 * AbstractCommand остановит выполнение команды, если игрок замучен
 * и нам необходимо, чтобы он был замучен/размучен.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectMutedCheck {

    /**
     * Сообщение, которое будет отправлено исполнителю
     * в случае, если он хочет замутить уже замученного игрока.
     */
    MessageManager alreadyMutedMessage() default MessageManager.ALREADY_MUTED;

    /**
     * Сообщение, которое будет отправлено исполнителю
     * в случае, если он хочет размутить незамученного игрока.
     */
    MessageManager alreadyUnmutedMessage() default MessageManager.ALREADY_UNMUTED;

    /**
     * Если нам важно, чтобы игрок был замучен.
     */
    boolean importantMuted();

    /**
     * Если нам важно, чтобы игрок был размучен.
     */
    boolean importantUnmuted();

    /**
     * Позиция аргумента, в которой указан ник, на который
     * выдается наказание.
     */
    int argPosition() default 0;

}
