package M0odiBans.M0odiBans.Objects.PunishedPlayers.Muted;

import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.MutedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import M0odiBans.M0odiBans.Utils.DateUtils;
import lombok.Getter;

public class TempMutedPlayer extends MutedPlayer {

    @Getter
    private final long unmuteTime; // Время, когда игрок будет автоматически размучен.

    public TempMutedPlayer(String nickname, String sender, String status, Reason reason, long unmuteTime) {
        super(nickname, sender, status, reason,
                // Broadcast Message
                MessageManager.TEMPMUTE_BROADCAST.getMessage()
                            .replaceAll("%sender%", sender)
                            .replaceAll("%target%", nickname)
                            .replaceAll("%reason%", reason.getReason())
                            .replaceAll("%description%", reason.getDescription())
                            .replaceAll("%date%", DateUtils.formatDate(unmuteTime)),
                // First Notify Message
                MessageManager.FIRST_TEMPMUTE_SCREEN.getMessage()
                            .replaceAll("%sender%", sender)
                            .replaceAll("%target%", nickname)
                            .replaceAll("%reason%", reason.getReason())
                            .replaceAll("%description%", reason.getDescription())
                            .replaceAll("%date%", DateUtils.formatDate(unmuteTime)),
                // Notify Message
                MessageManager.TEMPMUTE_SCREEN.getMessage()
                            .replaceAll("%sender%", sender)
                            .replaceAll("%target%", nickname)
                            .replaceAll("%reason%", reason.getReason())
                            .replaceAll("%description%", reason.getDescription())
                            .replaceAll("%date%", DateUtils.formatDate(unmuteTime)));
        this.unmuteTime = unmuteTime;
    }

}
