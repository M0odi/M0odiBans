package M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned;

import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.BannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import M0odiBans.M0odiBans.Utils.DateUtils;
import lombok.Getter;

public class TempBannedPlayer extends BannedPlayer {

    @Getter
    private final long unbanTime; // Время, когда игрок будет разблокирован.

    public TempBannedPlayer(String nickname, String sender, String status, Reason reason, long unbanTime) {
        super(nickname, sender, status, reason,
                // Broadcast Message
                MessageManager.TEMPBAN_BROADCAST.getMessage()
                        .replaceAll("%sender%", sender)
                        .replaceAll("%target%", nickname)
                        .replaceAll("%reason%", reason.getReason())
                        .replaceAll("%description%", reason.getDescription())
                        .replaceAll("%date%", DateUtils.formatDate(unbanTime)),
                // Ban Screen
                MessageManager.TEMPBAN_SCREEN.getMessage()
                        .replaceAll("%sender%", sender)
                        .replaceAll("%reason%", reason.getReason())
                        .replaceAll("%description%", reason.getDescription())
                        .replaceAll("%date%", DateUtils.formatDate(unbanTime)));
        this.unbanTime = unbanTime;
    }

}
