package M0odiBans.M0odiBans.Managers;

import M0odiBans.M0odiBans.M0odiBans;
import M0odiBans.M0odiBans.Utils.ChatUtils;

import java.util.List;

public enum MessageManager {

    EMPTY (""),

    COMMAND_NOT_EXISTS (M0odiBans.getMessages().getConfig().getString("commands.command-not-exists")),
    NOT_PERMS (M0odiBans.getMessages().getConfig().getString("commands.not-perms")),
    YOUR_SELF_USAGE (M0odiBans.getMessages().getConfig().getString("commands.your-self-usage")),
    PLAYER_NOT_ONLINE (M0odiBans.getMessages().getConfig().getString("commands.player-not-online")),
    YOU_LOW_PRIORITY (M0odiBans.getMessages().getConfig().getString("commands.you-low-priority")),
    IS_PRIORITY_PLAYER (M0odiBans.getMessages().getConfig().getString("commands.is-priority-player")),
    ALREADY_BANNED (M0odiBans.getMessages().getConfig().getString("commands.already-banned")),
    ALREADY_UNBANNED (M0odiBans.getMessages().getConfig().getString("commands.already-unbanned")),
    ALREADY_MUTED (M0odiBans.getMessages().getConfig().getString("commands.already-muted")),
    ALREADY_UNMUTED (M0odiBans.getMessages().getConfig().getString("commands.already-unmuted")),

    REASON_NOT_EXISTS (M0odiBans.getMessages().getConfig().getString("reasons.reason-not-exists")),
    REASON_NOT_ALLOWED_FOR_THIS_PUNISHMENT (M0odiBans.getMessages().getConfig().getString("reasons.reason-not-allowed-for-this-punishment")),


    INFO_KICK (M0odiBans.getMessages().getConfig().getString("info.kick")),
    INFO_BAN (M0odiBans.getMessages().getConfig().getString("info.ban")),
    INFO_TEMPBAN (M0odiBans.getMessages().getConfig().getString("info.tempban")),
    INFO_UNBAN (M0odiBans.getMessages().getConfig().getString("info.unban")),
    INFO_MUTE (M0odiBans.getMessages().getConfig().getString("info.mute")),
    INFO_TEMPMUTE (M0odiBans.getMessages().getConfig().getString("info.tempmute")),
    INFO_UNMUTE (M0odiBans.getMessages().getConfig().getString("info.unmute")),
    INFO_CHECK (M0odiBans.getMessages().getConfig().getString("info.check")),

    USAGE_KICK (M0odiBans.getMessages().getConfig().getString("usages.kick")),
    USAGE_BAN (M0odiBans.getMessages().getConfig().getString("usages.ban")),
    USAGE_TEMPBAN (M0odiBans.getMessages().getConfig().getString("usages.tempban")),
    USAGE_UNBAN (M0odiBans.getMessages().getConfig().getString("usages.unban")),
    USAGE_MUTE (M0odiBans.getMessages().getConfig().getString("usages.mute")),
    USAGE_TEMPMUTE (M0odiBans.getMessages().getConfig().getString("usages.tempmute")),
    USAGE_UNMUTE (M0odiBans.getMessages().getConfig().getString("usages.unmute")),
    USAGE_CHECK (M0odiBans.getMessages().getConfig().getString("usages.check")),

    KICK_BROADCAST (M0odiBans.getMessages().getConfig().getString("broadcasts.kick")),
    BAN_BROADCAST (M0odiBans.getMessages().getConfig().getString("broadcasts.ban")),
    TEMPBAN_BROADCAST (M0odiBans.getMessages().getConfig().getString("broadcasts.tempban")),
    UNBAN_BROADCAST (M0odiBans.getMessages().getConfig().getString("broadcasts.unban")),
    MUTE_BROADCAST (M0odiBans.getMessages().getConfig().getString("broadcasts.mute")),
    TEMPMUTE_BROADCAST (M0odiBans.getMessages().getConfig().getString("broadcasts.tempmute")),
    UNMUTE_BROADCAST (M0odiBans.getMessages().getConfig().getString("broadcasts.unmute")),

    KICK_SCREEN (M0odiBans.getMessages().getConfig().getStringList("screens.kick")),
    BAN_SCREEN (M0odiBans.getMessages().getConfig().getStringList("screens.ban")),
    TEMPBAN_SCREEN (M0odiBans.getMessages().getConfig().getStringList("screens.tempban")),
    FIRST_MUTE_SCREEN (M0odiBans.getMessages().getConfig().getString("screens.first-mute-screen")),
    MUTE_SCREEN (M0odiBans.getMessages().getConfig().getString("screens.mute-screen")),
    FIRST_TEMPMUTE_SCREEN (M0odiBans.getMessages().getConfig().getString("screens.first-tempmute-screen")),
    TEMPMUTE_SCREEN (M0odiBans.getMessages().getConfig().getString("screens.tempmute-screen")),

    STATUS_PERM_BAN (M0odiBans.getMessages().getConfig().getString("sender.status-perm-ban")),
    STATUS_TEMP_BAN (M0odiBans.getMessages().getConfig().getString("sender.status-temp-ban")),
    STATUS_UNBAN (M0odiBans.getMessages().getConfig().getString("sender.status-unban")),
    STATUS_PERM_MUTE (M0odiBans.getMessages().getConfig().getString("sender.status-perm-mute")),
    STATUS_TEMP_MUTE (M0odiBans.getMessages().getConfig().getString("sender.status-temp-mute")),
    STATUS_UNMUTE (M0odiBans.getMessages().getConfig().getString("sender.status-unmute")),

    RELOADED_SENDER (M0odiBans.getMessages().getConfig().getString("sender.reloaded")),
    KICK_SENDER (M0odiBans.getMessages().getConfig().getString("sender.kick")),
    BAN_SENDER (M0odiBans.getMessages().getConfig().getString("sender.ban")),
    TEMPBAN_SENDER (M0odiBans.getMessages().getConfig().getString("sender.tempban")),
    UNBAN_SENDER (M0odiBans.getMessages().getConfig().getString("sender.unban")),
    MUTE_SENDER (M0odiBans.getMessages().getConfig().getString("sender.mute")),
    TEMPMUTE_SENDER (M0odiBans.getMessages().getConfig().getString("sender.tempmute")),
    UNMUTE_SENDER (M0odiBans.getMessages().getConfig().getString("sender.unmute")),

    CHECK_SENDER (M0odiBans.getMessages().getConfig().getString("sender.check"));

    private final String message;

    MessageManager(String message) {
        this.message = message;
    }

    MessageManager(List<String> messageInList) {
        this.message = ChatUtils.fromListToString(messageInList, "\n");
    }

    @SuppressWarnings("ConstantConditions")
    public String getMessage() {

        //... Конвертируем цветовые коды, заменяем префиксы...
        return ChatUtils.convertColorCodes(message
                .replaceAll("%prefix%", M0odiBans.getMessages().getConfig().getString("prefix"))
                .replaceAll("%usage-prefix%", M0odiBans.getMessages().getConfig().getString("usages.usage-prefix")));

    }

}
