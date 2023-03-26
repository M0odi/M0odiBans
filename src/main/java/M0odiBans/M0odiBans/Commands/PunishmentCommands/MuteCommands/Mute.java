package M0odiBans.M0odiBans.Commands.PunishmentCommands.MuteCommands;

import M0odiBans.M0odiBans.Annotations.InjectAliasesCheck.InjectAliasesCheck;
import M0odiBans.M0odiBans.Annotations.InjectMutedCheck.InjectMutedCheck;
import M0odiBans.M0odiBans.Annotations.InjectPriorityCheck.InjectPriorityCheck;
import M0odiBans.M0odiBans.Annotations.InjectPriorityPermCheck.InjectPriorityPermCheck;
import M0odiBans.M0odiBans.Annotations.InjectReasonExistsCheck.InjectReasonExistsCheck;
import M0odiBans.M0odiBans.Annotations.InjectRequirements.InjectRequirements;
import M0odiBans.M0odiBans.Annotations.InjectYourSelfTarget.InjectYourSelfTarget;
import M0odiBans.M0odiBans.Commands.AbstractCommand;
import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Muted.PermMutedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import org.bukkit.command.CommandSender;

@InjectRequirements(permission = "M0odiBans.MUTE", usageMessage = MessageManager.USAGE_MUTE)
@InjectYourSelfTarget
@InjectReasonExistsCheck
@InjectAliasesCheck(punishment = "MUTE")
@InjectPriorityCheck
@InjectPriorityPermCheck
@InjectMutedCheck(importantMuted = false, importantUnmuted = true)
public class Mute extends AbstractCommand {

    public Mute() {
        super("mute");
    }

    @Override
    protected void executeCommand(CommandSender sender, String[] args) {

        PermMutedPlayer mutedPlayer = new PermMutedPlayer(args[0], sender.getName(),
                "perm", Reason.getReasonFromConfig(args[1]));

        M0odiBans.getDatabase().createMutedPlayer(mutedPlayer);

        mutedPlayer.firstNotifyPunished();
        mutedPlayer.broadcastAboutPunishment();

        sender.sendMessage(MessageManager.MUTE_SENDER.getMessage()
                .replaceAll("%target%", args[0]));

    }

    @Override
    protected boolean feasibilityCheck(CommandSender sender, String[] args) {
        return true;
    }

}
