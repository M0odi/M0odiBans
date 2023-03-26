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
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned.PermBannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import org.bukkit.command.CommandSender;

@InjectRequirements(permission = "M0odiBans.BAN", usageMessage = MessageManager.USAGE_BAN)
@InjectYourSelfTarget
@InjectReasonExistsCheck
@InjectAliasesCheck(punishment = "BAN")
@InjectPriorityCheck
@InjectPriorityPermCheck
@InjectBannedCheck(importantBanned = false, importantUnbanned = true)
public class Ban extends AbstractCommand {

    public Ban() {
        super("ban");
    }

    @Override
    protected void executeCommand(CommandSender sender, String[] args) {

        PermBannedPlayer bannedPlayer = new PermBannedPlayer(args[0], sender.getName(),
                "perm", Reason.getReasonFromConfig(args[1]));

        M0odiBans.getDatabase().createBannedPlayer(bannedPlayer);

        bannedPlayer.kickPlayer();
        bannedPlayer.broadcastAboutPunishment();

        sender.sendMessage(MessageManager.BAN_SENDER.getMessage()
                .replaceAll("%target%", args[0]));

    }

    @Override
    protected boolean feasibilityCheck(CommandSender sender, String[] args) {
        return true;
    }

}
