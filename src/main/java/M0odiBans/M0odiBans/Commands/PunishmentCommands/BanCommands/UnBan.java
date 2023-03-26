package M0odiBans.M0odiBans.Commands.PunishmentCommands.BanCommands;

import M0odiBans.M0odiBans.Annotations.InjectBannedCheck.InjectBannedCheck;
import M0odiBans.M0odiBans.Annotations.InjectRequirements.InjectRequirements;
import M0odiBans.M0odiBans.Annotations.InjectYourSelfTarget.InjectYourSelfTarget;
import M0odiBans.M0odiBans.Commands.AbstractCommand;
import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Utils.ChatUtils;
import org.bukkit.command.CommandSender;

@InjectRequirements(permission = "M0odiBans.UNBAN", lengthUsage = 1, usageMessage = MessageManager.USAGE_UNBAN)
@InjectYourSelfTarget
@InjectBannedCheck(importantBanned = true, importantUnbanned = false)
public class UnBan extends AbstractCommand {

    public UnBan() {
        super("unban");
    }

    @Override
    protected void executeCommand(CommandSender sender, String[] args) {

        M0odiBans.getDatabase().deleteBannedPlayer(args[0]);

        sender.sendMessage(MessageManager.UNBAN_SENDER.getMessage()
                .replaceAll("%target%", args[0]));

        ChatUtils.broadcastMessage(MessageManager.UNBAN_BROADCAST.getMessage()
                .replaceAll("%sender%", sender.getName())
                .replaceAll("%target%", args[0]));

    }

    @Override
    protected boolean feasibilityCheck(CommandSender sender, String[] args) {
        return true;
    }

}
