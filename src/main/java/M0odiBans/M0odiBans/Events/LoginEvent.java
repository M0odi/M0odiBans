package M0odiBans.M0odiBans.Events;

import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.BannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned.TempBannedPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginEvent implements Listener {

    @EventHandler
    public void onLoginEvent(PlayerLoginEvent event) {

        Player player = event.getPlayer();

        //... Получаем объект заблокированного игрока,
        // если возвращается null - данный игрок не заблокирован.
        BannedPlayer bannedPlayer = M0odiBans.getDatabase().getBannedPlayer(player.getName());
        if (bannedPlayer == null) return;

        //... Если игрок временно забанен, проверяем, не прошло ли время
        // для автоматического разбана, если пришло - разбаниваем...
        if (bannedPlayer instanceof TempBannedPlayer) {

            TempBannedPlayer tempBannedPlayer = (TempBannedPlayer) bannedPlayer;

            if (tempBannedPlayer.getUnbanTime() < System.currentTimeMillis()) {
                M0odiBans.getDatabase().deleteBannedPlayer(player.getName());
                return;
            }

        }

        //... Отключаем игрока...
        bannedPlayer.disallowPlayer(event);

    }

}
