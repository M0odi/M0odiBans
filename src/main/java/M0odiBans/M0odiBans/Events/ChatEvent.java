package M0odiBans.M0odiBans.Events;

import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.MutedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Muted.TempMutedPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChatEvent(PlayerChatEvent event) {

        Player player = event.getPlayer();

        //... Получаем объект замученного игрока,
        // если возвращается null - данный игрок не замучен.
        MutedPlayer mutedPlayer = M0odiBans.getDatabase().getMutedPlayer(player.getName());
        if (mutedPlayer == null) return;

        //... Если игрок временно замучен, проверяем, не прошло ли время
        // для автоматического размута, если пришло - размучиваем...
        if (mutedPlayer instanceof TempMutedPlayer) {

            TempMutedPlayer tempMutedPlayer = (TempMutedPlayer) mutedPlayer;

            if (tempMutedPlayer.getUnmuteTime() < System.currentTimeMillis()) {
                M0odiBans.getDatabase().deleteMutedPlayer(player.getName());
                return;
            }

        }

        //... Отменяем отправку сообщения...
        mutedPlayer.notifyPunished();
        event.setCancelled(true);

    }

    @EventHandler
    public void onPreprocessCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();

        //... Перебираем все запрещенные команды во время мута, и если игрок
        // ввел одну из таких - отменяем отправление.
        for (String forbiddenCommand : M0odiBans.getInstance().getConfig().getStringList("forbidden-commands")) {

            String[] splited = message.split(" ");

            if (splited[0].equalsIgnoreCase(forbiddenCommand)) {

                //... Получаем объект замученного игрока,
                // если возвращается null - данный игрок не замучен.
                MutedPlayer mutedPlayer = M0odiBans.getDatabase().getMutedPlayer(player.getName());
                if (mutedPlayer == null) return;

                //... Если игрок временно замучен, проверяем, не прошло ли время
                // для автоматического размута, если пришло - размучиваем...
                if (mutedPlayer instanceof TempMutedPlayer) {

                    TempMutedPlayer tempMutedPlayer = (TempMutedPlayer) mutedPlayer;

                    if (tempMutedPlayer.getUnmuteTime() < System.currentTimeMillis()) {
                        M0odiBans.getDatabase().deleteMutedPlayer(player.getName());
                        return;
                    }

                }

                //... Отменяем отправку сообщения...
                mutedPlayer.notifyPunished();
                event.setCancelled(true);

            }
        }


    }

}
