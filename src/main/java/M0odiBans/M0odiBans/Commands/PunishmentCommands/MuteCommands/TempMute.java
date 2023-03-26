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
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Muted.TempMutedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import org.bukkit.command.CommandSender;

@InjectRequirements(permission = "M0odiBans.TEMPMUTE", usageMessage = MessageManager.USAGE_TEMPMUTE)
@InjectYourSelfTarget
@InjectReasonExistsCheck
@InjectAliasesCheck(punishment = "TEMPMUTE")
@InjectPriorityCheck
@InjectPriorityPermCheck
@InjectMutedCheck(importantMuted = false, importantUnmuted = true)
public class TempMute extends AbstractCommand {

    public TempMute() {
        super("tempmute");
    }

    @Override @SuppressWarnings("ConstantConditions")
    protected void executeCommand(CommandSender sender, String[] args) {

        Reason reason = Reason.getReasonFromConfig(args[1]);

        TempMutedPlayer mutedPlayer = new TempMutedPlayer(args[0], sender.getName(),
                "temp", reason, System.currentTimeMillis() + reason.getTime());

        M0odiBans.getDatabase().createMutedPlayer(mutedPlayer);

        mutedPlayer.firstNotifyPunished();
        mutedPlayer.broadcastAboutPunishment();

        sender.sendMessage(MessageManager.TEMPMUTE_SENDER.getMessage()
                .replaceAll("%target%", args[0]));

    }

    @Override
    protected boolean feasibilityCheck(CommandSender sender, String[] args) {
        return true;
    }

}
