package M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned;

import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.BannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;

public class KickedPlayer extends BannedPlayer {

    public KickedPlayer(String nickname, String sender, String status, Reason reason) {
        super(nickname, sender, status, reason,
                //... Broadcast Message
                MessageManager.KICK_BROADCAST.getMessage()
                        .replaceAll("%sender%", sender)
                        .replaceAll("%target%", nickname)
                        .replaceAll("%reason%", reason.getReason())
                        .replaceAll("%description%", reason.getDescription()),
                //... Kick Screen
                MessageManager.KICK_SCREEN.getMessage()
                        .replaceAll("%sender%", sender)
                        .replaceAll("%reason%", reason.getReason())
                        .replaceAll("%description%", reason.getDescription()));
    }

}
