package M0odiBans.M0odiBans.Commands.PunishmentCommands;

import M0odiBans.M0odiBans.Annotations.InjectAliasesCheck.InjectAliasesCheck;
import M0odiBans.M0odiBans.Annotations.InjectPriorityCheck.InjectPriorityCheck;
import M0odiBans.M0odiBans.Annotations.InjectPriorityPermCheck.InjectPriorityPermCheck;
import M0odiBans.M0odiBans.Annotations.InjectReasonExistsCheck.InjectReasonExistsCheck;
import M0odiBans.M0odiBans.Annotations.InjectRequirements.InjectRequirements;
import M0odiBans.M0odiBans.Annotations.InjectYourSelfTarget.InjectYourSelfTarget;
import M0odiBans.M0odiBans.Commands.AbstractCommand;
import M0odiBans.M0odiBans.Managers.MessageManager;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned.KickedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@InjectRequirements(permission = "M0odiBans.KICK", usageMessage = MessageManager.USAGE_KICK)
@InjectYourSelfTarget
@InjectReasonExistsCheck
@InjectAliasesCheck(punishment = "KICK")
@InjectPriorityCheck
@InjectPriorityPermCheck
public class Kick extends AbstractCommand {

    public Kick() {
        super("kick");
    }

    @Override
    protected void executeCommand(CommandSender sender, String[] args) {

        KickedPlayer kickedPlayer = new KickedPlayer(args[0], sender.getName(),
                "kicked", Reason.getReasonFromConfig(args[1]));

        kickedPlayer.kickPlayer();
        kickedPlayer.broadcastAboutPunishment();

        sender.sendMessage(MessageManager.KICK_SENDER.getMessage()
                .replaceAll("%target%", args[0]));

    }

    @Override
    protected boolean feasibilityCheck(CommandSender sender, String[] args) {

        //... Если игрок, которого кикают, сейчас не в сети,
        // отменяем исполнение команды...
        if (Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(MessageManager.PLAYER_NOT_ONLINE.getMessage());
            return false;
        }

        return true;

    }

}
