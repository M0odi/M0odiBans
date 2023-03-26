package M0odiBans.M0odiBans.Annotations.InjectMutedCheck;

import M0odiBans.M0odiBans.Annotations.Validator;
import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.MutedPlayer;
import org.bukkit.command.CommandSender;

import java.lang.annotation.Annotation;

public class InjectMutedCheckValidator implements Validator {

    @Override
    public boolean validate(Annotation annotation, CommandSender sender, String[] args) {

        //... Получение необходимых данных для работы...
        InjectMutedCheck injectMutedCheck = (InjectMutedCheck) annotation;

        boolean importantMuted = injectMutedCheck.importantMuted();
        boolean importantUnmuted = injectMutedCheck.importantUnmuted();
        int argPosition = injectMutedCheck.argPosition();
        String alreadyMutedMessage = injectMutedCheck.alreadyMutedMessage().getMessage();
        String alreadyUnmutedMessage = injectMutedCheck.alreadyUnmutedMessage().getMessage();

        MutedPlayer mutedPlayer = M0odiBans.getDatabase().getMutedPlayer(args[argPosition]);

        //... Если игрок замучен и нам необходимо, чтобы он был размучен - отменяем исполнение команды...
        if (mutedPlayer != null && importantUnmuted) {
            sender.sendMessage(alreadyMutedMessage);
            return false;
        }

        //... Если игрок не замучен и нам необходимо, чтобы он был замучен - отменяем исполнение команды...
        if (mutedPlayer == null && importantMuted) {
            sender.sendMessage(alreadyUnmutedMessage);
            return false;
        }

        //... Условия соблюдены, продолжаем исполнение...
        return true;

    }

}
