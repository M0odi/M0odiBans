package M0odiBans.M0odiBans.Commands;

import M0odiBans.M0odiBans.Annotations.InjectRequirements.InjectRequirements;
import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Managers.MessageManager;
import org.bukkit.command.CommandSender;

@InjectRequirements(lengthUsage = 0, usageMessage = MessageManager.EMPTY)
public class MBCommand extends AbstractCommand {

    public MBCommand() {
        super("mb");
    }

    @Override
    protected void executeCommand(CommandSender sender, String[] args) {

        if (args.length == 0) {

            sender.sendMessage(MessageManager.INFO_KICK.getMessage());
            sender.sendMessage(MessageManager.INFO_BAN.getMessage());
            sender.sendMessage(MessageManager.INFO_TEMPBAN.getMessage());
            sender.sendMessage(MessageManager.INFO_UNBAN.getMessage());
            sender.sendMessage(MessageManager.INFO_MUTE.getMessage());
            sender.sendMessage(MessageManager.INFO_TEMPMUTE.getMessage());
            sender.sendMessage(MessageManager.INFO_UNMUTE.getMessage());
            sender.sendMessage(MessageManager.INFO_CHECK.getMessage());

        } else if (args[0].equalsIgnoreCase("reload")) {

            M0odiBans.init();
            sender.sendMessage(MessageManager.RELOADED_SENDER.getMessage());

        } else {

            sender.sendMessage(MessageManager.COMMAND_NOT_EXISTS.getMessage());

        }

    }

    @Override
    protected boolean feasibilityCheck(CommandSender sender, String[] args) {

        //... Если игрок хочет перезагрузить плагин и у него нет прав на это, отменяем исполнение...
        if (args.length >= 1 && args[0].equalsIgnoreCase("reload") && !sender.hasPermission("M0odiBans.ADMIN")) {
            sender.sendMessage(MessageManager.NOT_PERMS.getMessage());
            return false;
        }

        return true;

    }
}
