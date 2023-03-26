package M0odiBans.M0odiBans;

import M0odiBans.M0odiBans.Managers.TimerManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.BannedPlayer;
import org.bukkit.Bukkit;

public class Timer implements TimerManager {

    @Override
    public void executeTimer() {

        Bukkit.getOnlinePlayers().forEach(player -> {

            BannedPlayer bannedPlayer = M0odiBans.getDatabase().getBannedPlayer(player.getName());
            if (bannedPlayer == null) return;

            bannedPlayer.kickPlayer();
            bannedPlayer.broadcastAboutPunishment();

        });

    }

}
