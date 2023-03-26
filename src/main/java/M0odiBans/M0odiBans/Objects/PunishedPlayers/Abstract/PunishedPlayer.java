package M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract;

import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import M0odiBans.M0odiBans.Utils.ChatUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public abstract class PunishedPlayer {

    private final String nickname; // Ник игрока, которому было выдано наказание.

    private final String sender; // Ник игрока, который выдал наказание.

    private final String status; // Статус (временно-перманентно забанен-замучен)

    private final Reason reason; // Причина, по которой был забанен.

    private final String broadcastMessage;

    /**
     * Оповещение сервера о наказании.
     */
    public void broadcastAboutPunishment() {
        ChatUtils.broadcastMessage(broadcastMessage);
    }

}
