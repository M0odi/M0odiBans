package M0odiBans.M0odiBans.Commands.PunishmentCommands;

import M0odiBans.M0odiBans.Annotations.InjectRequirements.InjectRequirements;
import M0odiBans.M0odiBans.Commands.AbstractCommand;
import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.BannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.MutedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned.TempBannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Muted.TempMutedPlayer;
import M0odiBans.M0odiBans.Utils.DateUtils;
import org.bukkit.command.CommandSender;

@InjectRequirements(permission = "M0odiBans.CHECK", lengthUsage = 1, usageMessage = MessageManager.USAGE_CHECK)
public class Check extends AbstractCommand {

    public Check() {
        super("check");
    }

    @Override
    protected void executeCommand(CommandSender sender, String[] args) {

        String statusBan;

        BannedPlayer bannedPlayer = M0odiBans.getDatabase().getBannedPlayer(args[0]);

        if (bannedPlayer == null) {
            statusBan = MessageManager.STATUS_UNBAN.getMessage();
        } else if (bannedPlayer instanceof TempBannedPlayer) {
            TempBannedPlayer tempBannedPlayer = (TempBannedPlayer) bannedPlayer;
            statusBan = MessageManager.STATUS_TEMP_BAN.getMessage()
                    .replaceAll("%sender%", bannedPlayer.getSender())
                    .replaceAll("%date%", DateUtils.formatDate(tempBannedPlayer.getUnbanTime()))
                    .replaceAll("%reason%", bannedPlayer.getReason().getReason());
        } else {
            statusBan = MessageManager.STATUS_PERM_BAN.getMessage()
                    .replaceAll("%sender%", bannedPlayer.getSender())
                    .replaceAll("%reason%", bannedPlayer.getReason().getReason());
        }

        String statusMute;

        MutedPlayer mutedPlayer = M0odiBans.getDatabase().getMutedPlayer(args[0]);

        if (mutedPlayer == null) {
            statusMute = MessageManager.STATUS_UNMUTE.getMessage();
        } else if (mutedPlayer instanceof TempMutedPlayer) {
            TempMutedPlayer tempMutedPlayer = (TempMutedPlayer) mutedPlayer;
            statusMute = MessageManager.STATUS_TEMP_MUTE.getMessage()
                    .replaceAll("%sender%", mutedPlayer.getSender())
                    .replaceAll("%date%", DateUtils.formatDate(tempMutedPlayer.getUnmuteTime()))
                    .replaceAll("%reason%", mutedPlayer.getReason().getReason());
        } else {
            statusMute = MessageManager.STATUS_PERM_MUTE.getMessage()
                    .replaceAll("%sender%", mutedPlayer.getSender())
                    .replaceAll("%reason%", mutedPlayer.getReason().getReason());
        }

        sender.sendMessage(MessageManager.CHECK_SENDER.getMessage()
                .replaceAll("%player%", args[0])
                .replaceAll("%status-ban%", statusBan)
                .replaceAll("%status-mute%", statusMute ));

    }

    @Override
    protected boolean feasibilityCheck(CommandSender sender, String[] args) {
        return true;
    }

}
