package M0odiBans.M0odiBans;

import M0odiBans.M0odiBans.Commands.MBCommand;
import M0odiBans.M0odiBans.Commands.PunishmentCommands.BanCommands.Ban;
import M0odiBans.M0odiBans.Commands.PunishmentCommands.BanCommands.TempBan;
import M0odiBans.M0odiBans.Commands.PunishmentCommands.BanCommands.UnBan;
import M0odiBans.M0odiBans.Commands.PunishmentCommands.Check;
import M0odiBans.M0odiBans.Commands.PunishmentCommands.Kick;
import M0odiBans.M0odiBans.Commands.PunishmentCommands.MuteCommands.Mute;
import M0odiBans.M0odiBans.Commands.PunishmentCommands.MuteCommands.TempMute;
import M0odiBans.M0odiBans.Commands.PunishmentCommands.MuteCommands.UnMute;
import M0odiBans.M0odiBans.Database.DatabaseBridge;
import M0odiBans.M0odiBans.Database.MySQL.MySQL_Bridge;
import M0odiBans.M0odiBans.Events.ChatEvent;
import M0odiBans.M0odiBans.Events.LoginEvent;
import M0odiBans.M0odiBans.Managers.ConfigManager;
import M0odiBans.M0odiBans.Utils.LogUtils;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class M0odiBans extends JavaPlugin {

    @Getter
    private static M0odiBans instance;

    @Getter
    private static ConfigManager messages, reasons;

    @Getter
    private static DatabaseBridge database;

    @Override
    public void onEnable() {

        instance = this;

        luckPermsCheck();

        init();

        Bukkit.getPluginManager().registerEvents(new LoginEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);

        if (getConfig().getInt("delay") != -1) {
            new Timer().startTimer(20, getConfig().getInt("delay") * 20);
        }

    }

    private static void luckPermsCheck() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) {
            System.out.println(LogUtils.prefix + "Не обнаружено LuckPerms. Отключение...");
            Bukkit.getPluginManager().disablePlugin(getInstance());
        }
    }

    /**
     * Инициализация ресурсов для работы плагина.
     */
    public static void init() {

        LogUtils.printTitleInConsole();
        LogUtils.printInfoAboutPluginInConsole();
        Bukkit.getLogger().info(LogUtils.prefix + "Инициализация ресурсов...");

        initConfigs();
        initCommands();
        initDB();

    }

    /**
     * Инициализация конфигов.
     */
    private static void initConfigs() {

        Bukkit.getLogger().info(LogUtils.prefix + "Инициализация файлов конфигурации...");

        getInstance().saveDefaultConfig();

        messages = new ConfigManager("messages.yml");
        messages.save();

        reasons = new ConfigManager("reasons.yml");
        reasons.save();

    }

    /**
     * Инициализация базы данных.
     */
    private static void initDB() {

        Bukkit.getLogger().info(LogUtils.prefix + "Подключение к базе данных...");

        database = new MySQL_Bridge();
        database.initDatabase();

    }

    /**
     * Инициализация команд.
     */
    private static void initCommands() {

        Bukkit.getLogger().info(LogUtils.prefix + "Инициализация команд...");

        new MBCommand();
        new Kick();
        new Ban();
        new TempBan();
        new UnBan();
        new Mute();
        new TempMute();
        new UnMute();
        new Check();

    }

    @Override
    public void onDisable() {}

}
