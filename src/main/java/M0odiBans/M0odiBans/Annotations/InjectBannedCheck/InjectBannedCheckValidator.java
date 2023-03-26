package M0odiBans.M0odiBans.Annotations.InjectBannedCheck;

import M0odiBans.M0odiBans.Annotations.Validator;
import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.BannedPlayer;
import org.bukkit.command.CommandSender;

import java.lang.annotation.Annotation;

public class InjectBannedCheckValidator implements Validator {

    @Override
    public boolean validate(Annotation annotation, CommandSender sender, String[] args) {

        //... Получение необходимых данных для работы...
        InjectBannedCheck injectBannedCheck = (InjectBannedCheck) annotation;

        boolean importantBanned = injectBannedCheck.importantBanned();
        boolean importantUnbanned = injectBannedCheck.importantUnbanned();
        int argPosition = injectBannedCheck.argPosition();
        String alreadyBannedMessage = injectBannedCheck.alreadyBannedMessage().getMessage();
        String alreadyUnbannedMessage = injectBannedCheck.alreadyUnbannedMessage().getMessage();

        BannedPlayer bannedPlayer = M0odiBans.getDatabase().getBannedPlayer(args[argPosition]);

        //... Если игрок забанен и нам необходимо, чтобы он был не в бане - отменяем исполнение команды...
        if (bannedPlayer != null && importantUnbanned) {
            sender.sendMessage(alreadyBannedMessage);
            return false;
        }

        //... Если игрок не забанен и нам необходимо, чтобы он был забанен - отменяем исполнение команды...
        if (bannedPlayer == null && importantBanned) {
            sender.sendMessage(alreadyUnbannedMessage);
            return false;
        }

        //... Условия соблюдены, продолжаем исполнение...
        return true;

    }

}
