package M0odiBans.M0odiBans.Commands.PunishmentCommands.MuteCommands;

import M0odiBans.M0odiBans.Annotations.InjectMutedCheck.InjectMutedCheck;
import M0odiBans.M0odiBans.Annotations.InjectRequirements.InjectRequirements;
import M0odiBans.M0odiBans.Annotations.InjectYourSelfTarget.InjectYourSelfTarget;
import M0odiBans.M0odiBans.Commands.AbstractCommand;
import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Utils.ChatUtils;
import org.bukkit.command.CommandSender;

@InjectRequirements(permission = "M0odiBans.UNMUTE", lengthUsage = 1, usageMessage = MessageManager.USAGE_UNMUTE)
@InjectYourSelfTarget
@InjectMutedCheck(importantMuted = true, importantUnmuted = false)
public class UnMute extends AbstractCommand {

    public UnMute() {
        super("unmute");
    }

    @Override
    protected void executeCommand(CommandSender sender, String[] args) {

        M0odiBans.getDatabase().deleteMutedPlayer(args[0]);

        sender.sendMessage(MessageManager.UNMUTE_SENDER.getMessage()
                .replaceAll("%target%", args[0]));

        ChatUtils.broadcastMessage(MessageManager.UNMUTE_BROADCAST.getMessage()
                .replaceAll("%sender%", sender.getName())
                .replaceAll("%target%", args[0]));

    }

    @Override
    protected boolean feasibilityCheck(CommandSender sender, String[] args) {
        return true;
    }

}
