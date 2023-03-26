package M0odiBans.M0odiBans.Commands.PunishmentCommands.BanCommands;

import M0odiBans.M0odiBans.Annotations.InjectAliasesCheck.InjectAliasesCheck;
import M0odiBans.M0odiBans.Annotations.InjectBannedCheck.InjectBannedCheck;
import M0odiBans.M0odiBans.Annotations.InjectPriorityCheck.InjectPriorityCheck;
import M0odiBans.M0odiBans.Annotations.InjectPriorityPermCheck.InjectPriorityPermCheck;
import M0odiBans.M0odiBans.Annotations.InjectReasonExistsCheck.InjectReasonExistsCheck;
import M0odiBans.M0odiBans.Annotations.InjectRequirements.InjectRequirements;
import M0odiBans.M0odiBans.Annotations.InjectYourSelfTarget.InjectYourSelfTarget;
import M0odiBans.M0odiBans.Commands.AbstractCommand;
import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned.TempBannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import org.bukkit.command.CommandSender;

@InjectRequirements(permission = "M0odiBans.TEMPBAN", usageMessage = MessageManager.USAGE_TEMPBAN)
@InjectYourSelfTarget
@InjectReasonExistsCheck
@InjectAliasesCheck(punishment = "TEMPBAN")
@InjectPriorityCheck
@InjectPriorityPermCheck
@InjectBannedCheck(importantBanned = false, importantUnbanned = true)
public class TempBan extends AbstractCommand {

    public TempBan() {
        super("tempban");
    }

    @Override @SuppressWarnings("ConstantConditions")
    protected void executeCommand(CommandSender sender, String[] args) {

        Reason reason = Reason.getReasonFromConfig(args[1]);

        TempBannedPlayer bannedPlayer = new TempBannedPlayer(args[0], sender.getName(),
                "temp", reason, System.currentTimeMillis() + reason.getTime());

        M0odiBans.getDatabase().createBannedPlayer(bannedPlayer);

        bannedPlayer.kickPlayer();
        bannedPlayer.broadcastAboutPunishment();

        sender.sendMessage(MessageManager.TEMPBAN_SENDER.getMessage()
                .replaceAll("%target%", args[0]));

    }

    @Override
    protected boolean feasibilityCheck(CommandSender sender, String[] args) {
        return true;
    }

}
