package M0odiBans.M0odiBans.Objects.PunishedPlayers.Muted;

import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.MutedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;

public class PermMutedPlayer extends MutedPlayer {

    public PermMutedPlayer(String nickname, String sender, String status, Reason reason) {
        super(nickname, sender, status, reason,
                // Broadcast Message
                MessageManager.MUTE_BROADCAST.getMessage()
                        .replaceAll("%sender%", sender)
                        .replaceAll("%target%", nickname)
                        .replaceAll("%reason%", reason.getReason())
                        .replaceAll("%description%", reason.getDescription()),
                // First Notify Message
                MessageManager.FIRST_MUTE_SCREEN.getMessage()
                        .replaceAll("%sender%", sender)
                        .replaceAll("%reason%", reason.getReason())
                        .replaceAll("%description%", reason.getDescription()),
                // Notify Message
                MessageManager.MUTE_SCREEN.getMessage()
                        .replaceAll("%sender%", sender)
                        .replaceAll("%reason%", reason.getReason())
                        .replaceAll("%description%", reason.getDescription()));
    }


}
