package M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract;

import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;

public abstract class BannedPlayer extends PunishedPlayer {

    private final String kickMessage;

    public BannedPlayer(String nickname, String sender, String status, Reason reason,
                        String broadcastMessage, String kickMessage) {
        super(nickname, sender, status, reason, broadcastMessage);
        this.kickMessage = kickMessage;
    }

    /**
     * Отключить игрока от сервера при его попытке на него войти.
     */
    public void disallowPlayer(PlayerLoginEvent event) {
        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, kickMessage);
    }

    /**
     * Отключить игрока от сервера, если он в данный момент на нем.
     */
    public void kickPlayer() {

        Player target = Bukkit.getPlayer(getNickname());
        if (target == null) return;

        target.kickPlayer(kickMessage);

    }

}
