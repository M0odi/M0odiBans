package M0odiBans.M0odiBans.Database.MySQL;

import M0odiBans.M0odiBans.Database.DatabaseBridge;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.BannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.MutedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned.PermBannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Banned.TempBannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Muted.PermMutedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Muted.TempMutedPlayer;
import M0odiBans.M0odiBans.Objects.PunishmentComponents.Reason;
import M0odiBans.M0odiBans.Utils.LogUtils;

import java.sql.*;

public class MySQL_Bridge extends MySQL_Constants implements DatabaseBridge {

    private Connection connection;

    @Override
    public Connection getConnection() {

        //... Если подключение уже есть, возвращаем его...
        if (connection != null) return connection;

        //... Пытается подключиться к базе данных...
        try {

            return DriverManager.getConnection("jdbc:mysql://" + getHost() + "/"
                    + getDatabase() + "?characterEncoding=utf-8&autoReconnect=true&useSSL=false", getUser(), getPassword());

        } catch (SQLException ex) {

            ex.printStackTrace();

            System.out.println(LogUtils.prefix + "Ошибка подключения к базе данных. " +
                    "Возможна, база данных отключена?");

            return null;

        }

    }

    @Override
    public void initDatabase() {

        //... Подключаемся к базе данных и инициализируем ее...
        try (Statement statement = getConnection().createStatement()) {

            //... Создание таблицы `Bans` с полями:
            // `nickname` - никнейм наказанного игрока. (PRIMARY KEY, NOT NULL)
            // `sender` - никнейм игрока, который выдал наказание. (NOT NULL)
            // `reason` - причина, по которой наказание было выдан. (NOT NULL)
            // `unban_time` - время автоматического разбана (если необходимо).
            String initBans = "CREATE TABLE IF NOT EXISTS `Bans` " +
                    "(`nickname` VARCHAR(36) PRIMARY KEY NOT NULL, " +
                    "`status` VARCHAR(16) NOT NULL, " +
                    "`sender` VARCHAR(36) NOT NULL, " +
                    "`reason` VARCHAR(12) NOT NULL, " +
                    "`unban_time` BIGINT)";

            //... Создание таблицы `Muts` с полями:
            // `nickname` - никнейм наказанного игрока. (PRIMARY KEY, NOT NULL)
            // `sender` - никнейм игрока, который выдал наказание. (NOT NULL)
            // `reason` - причина, по которой наказание было выдан. (NOT NULL)
            // `unmute_time` - время автоматического размута (если необходимо).
            String initMuts = "CREATE TABLE IF NOT EXISTS `Muts` " +
                    "(`nickname` VARCHAR(36) PRIMARY KEY NOT NULL, " +
                    "`status` VARCHAR(16) NOT NULL, " +
                    "`sender` VARCHAR(36) NOT NULL, " +
                    "`reason` VARCHAR(12) NOT NULL, " +
                    "`unmute_time` BIGINT)";

            //... Выполнение запросов...
            statement.execute(initBans);
            statement.execute(initMuts);


        } catch (SQLException ex) {

            ex.printStackTrace();

            System.out.println(LogUtils.prefix + "Ошибка инициализации базы данных.");

        }

    }

    @Override
    public BannedPlayer getBannedPlayer(String nickname) {

        //... Подключаемся к базе данных, формируем запрос...
        try (PreparedStatement statement = getConnection().prepareStatement
                ("SELECT * FROM `Bans` WHERE `nickname` = ?")) {

            //... Заполняем запрос аргументами...
            statement.setString(1, nickname.toLowerCase());

            //... Выполняем запрос, получаем ответ...
            ResultSet result = statement.executeQuery();

            //... Если ответ пустой - игрока нет в базе данных, возвращаем null.
            if (!result.next()) return null;

            //... Игрок есть, получаем данные для дальнейшей работы...
            String status = result.getString("status");
            String sender = result.getString("sender");
            Reason reason = Reason.getReasonFromConfig(result.getString("reason"));
            long unbanTime = result.getLong("unban_time");

            //... Ориентируясь на статус, возвращаем нужный объект...
            switch (status.toLowerCase()) {
                case "perm":
                    return new PermBannedPlayer(nickname, sender, status, reason);
                case "temp":
                    return new TempBannedPlayer(nickname, sender, status, reason, unbanTime);
                default:
                    return null;
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            System.out.println(LogUtils.prefix + "Ошибка при получении данных из базы данных.");

        }

        return null;

    }

    @Override
    public MutedPlayer getMutedPlayer(String nickname) {

        //... Подключаемся к базе данных, формируем запрос...
        try (PreparedStatement statement = getConnection().prepareStatement
                ("SELECT * FROM `Muts` WHERE `nickname` = ?")) {

            //... Заполняем запрос аргументами...
            statement.setString(1, nickname.toLowerCase());

            //... Выполняем запрос, получаем ответ...
            ResultSet result = statement.executeQuery();

            //... Если ответ пустой - игрока нет в базе данных, возвращаем null.
            if (!result.next()) return null;

            //... Игрок есть, получаем данные для дальнейшей работы...
            String status = result.getString("status");
            String sender = result.getString("sender");
            Reason reason = Reason.getReasonFromConfig(result.getString("reason"));
            long unmuteTime = result.getLong("unmute_time");

            //... Ориентируясь на статус, возвращаем нужный объект...
            switch (status.toLowerCase()) {
                case "perm":
                    return new PermMutedPlayer(nickname, sender, status, reason);
                case "temp":
                    return new TempMutedPlayer(nickname, sender, status, reason, unmuteTime);
                default:
                    return null;
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            System.out.println(LogUtils.prefix + "Ошибка при получении данных из базы данных.");

        }

        return null;

    }

    @Override
    public void createBannedPlayer(BannedPlayer bannedPlayer) {

        //... Подключаемся к базе данных, формируем запрос...
        try (PreparedStatement statement = getConnection().prepareStatement
                ("INSERT INTO `Bans` (nickname, status, reason, sender, unban_time) " +
                        "VALUES (?, ?, ?, ?, ?)")) {

            //... Наполняем запрос аргументами...
            statement.setString(1, bannedPlayer.getNickname().toLowerCase());
            statement.setString(2, bannedPlayer.getStatus().toLowerCase());
            statement.setString(3, bannedPlayer.getReason().getReason());
            statement.setString(4, bannedPlayer.getSender().toLowerCase());

            //... Если данный объект представляет временно забаненного игрока,
            // то записываем и время автоматического разбана...
            if (bannedPlayer instanceof TempBannedPlayer) {

                TempBannedPlayer tempBannedPlayer = (TempBannedPlayer) bannedPlayer;
                statement.setLong(5, tempBannedPlayer.getUnbanTime());

            //... Иначе нет необходимости в указании времени автоматического разбана,
            // записываем null...
            } else {

                statement.setObject(5, null);

            }

            //... Выполняем запрос...
            statement.execute();

        } catch (SQLException ex) {

            ex.printStackTrace();

            System.out.println(LogUtils.prefix + "Ошибка при создании данных в базе данных");

        }

    }

    @Override
    public void createMutedPlayer(MutedPlayer mutedPlayer) {

        //... Подключаемся к базе данных, формируем запрос...
        try (PreparedStatement statement = getConnection().prepareStatement
                ("INSERT INTO `Muts` (nickname, status, reason, sender, unmute_time) " +
                        "VALUES (?, ?, ?, ?, ?)")) {

            //... Наполняем запрос аргументами...
            statement.setString(1, mutedPlayer.getNickname().toLowerCase());
            statement.setString(2, mutedPlayer.getStatus().toLowerCase());
            statement.setString(3, mutedPlayer.getReason().getReason());
            statement.setString(4, mutedPlayer.getSender().toLowerCase());

            //... Если данный объект представляет временно замученного игрока,
            // то записываем и время автоматического размута...
            if (mutedPlayer instanceof TempMutedPlayer) {

                TempMutedPlayer tempMutedPlayer = (TempMutedPlayer) mutedPlayer;
                statement.setLong(5, tempMutedPlayer.getUnmuteTime());

            //... Иначе нет необходимости в указании времени автоматического размута,
            // записываем null...
            } else {

                statement.setObject(5, null);

            }

            //... Выполняем запрос...
            statement.execute();

        } catch (SQLException ex) {

            ex.printStackTrace();

            System.out.println(LogUtils.prefix + "Ошибка при создании данных в базе данных");

        }

    }

    @Override
    public void deleteBannedPlayer(String nickname) {

        //... Подключаемся к базе данных. Формируем запрос...
        try (PreparedStatement statement = getConnection().prepareStatement
                ("DELETE FROM `Bans` WHERE `nickname` = ?")) {

            //... Наполняем запрос аргументами...
            statement.setString(1, nickname.toLowerCase());

            //... Выполняем запрос...
            statement.execute();

        } catch (SQLException ex) {

            ex.printStackTrace();

            System.out.println(LogUtils.prefix + "Ошибка при удалении данных в базе данных");

        }

    }

    @Override
    public void deleteMutedPlayer(String nickname) {

        //... Подключаемся к базе данных. Формируем запрос...
        try (PreparedStatement statement = getConnection().prepareStatement
                ("DELETE FROM `Muts` WHERE `nickname` = ?")) {

            //... Наполняем запрос аргументами...
            statement.setString(1, nickname.toLowerCase());

            //... Выполняем запрос...
            statement.execute();

        } catch (SQLException ex) {

            ex.printStackTrace();

            System.out.println(LogUtils.prefix + "Ошибка при удалении данных в базе данных");

        }

    }

}
