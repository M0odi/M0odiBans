package M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned;

import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.BannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;

public class PermBannedPlayer extends BannedPlayer {

    public PermBannedPlayer(String nickname, String sender, String status, Reason reason) {
        super(nickname, sender, status, reason,
                // Broadcast Message
                MessageManager.BAN_BROADCAST.getMessage()
                            .replaceAll("%sender%", sender)
                            .replaceAll("%target%", nickname)
                            .replaceAll("%reason%", reason.getReason())
                            .replaceAll("%description%", reason.getDescription()),
                // Ban Screen
                MessageManager.BAN_SCREEN.getMessage()
                        .replaceAll("%sender%", sender)
                        .replaceAll("%reason%", reason.getReason())
                        .replaceAll("%description%", reason.getDescription()));
    }

}