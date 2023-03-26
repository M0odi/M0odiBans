package M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract;

import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class MutedPlayer extends PunishedPlayer {

    private final String firstNotifyMessage;

    private final String notifyMessage;

    public MutedPlayer(String nickname, String sender, String status, Reason reason,
                       String broadcastMessage, String firstNotifyMessage, String notifyMessage) {
        super(nickname, sender, status, reason, broadcastMessage);
        this.firstNotifyMessage = firstNotifyMessage;
        this.notifyMessage = notifyMessage;
    }

    /**
     * Оповестить игрока на сервере о том, что он был замучен.
     */
    public void firstNotifyPunished() {

        Player target = Bukkit.getPlayer(getNickname());
        if (target == null) return;

        target.sendMessage(firstNotifyMessage);

    }

    /**
     * Оповестить игрока о том, что он не может писать в чат,
     * так как замучен.
     */
    public void notifyPunished() {

        Player target = Bukkit.getPlayer(getNickname());
        if (target == null) return;

        target.sendMessage(notifyMessage);

    }

}
