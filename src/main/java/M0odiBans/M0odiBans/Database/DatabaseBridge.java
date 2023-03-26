package M0odiBans.M0odiBans.Database;

import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.BannedPlayer;
import M0odiBans.M0odiBans.Objects.PunishedPlayers.Abstract.MutedPlayer;

import java.sql.Connection;

public interface DatabaseBridge {

    /**
     * @return Подключение к базе данных.
     */
    Connection getConnection();

    /**
     * Инициализация таблиц в базе данных для работы с ней.
     */
    void initDatabase();

    /**
     * Получение объекта забаненного игрока по его нику.
     *
     * @param nickname Ник игрока.
     * @return BannedPlayer, если игрок забанен,
     *         null - если нет.
     */
    BannedPlayer getBannedPlayer(String nickname);

    /**
     * Получение объекта замученного игрока по его нику.
     *
     * @param nickname Ник игрока.
     * @return MutedPlayer, если игрок замучен,
     *         null - если нет.
     */
    MutedPlayer getMutedPlayer(String nickname);

    /**
     * Создание записи об игроке в базе данных.
     *
     * @param bannedPlayer Объект забаненного игрока.
     */
    void createBannedPlayer(BannedPlayer bannedPlayer);

    /**
     * Создание записи об игроке в базе данных.
     *
     * @param mutedPlayer Объект замученного игрока.
     */
    void createMutedPlayer(MutedPlayer mutedPlayer);

    /**
     * Удаление записи об забаненном игроке в базе данных.
     *
     * @param nickname Ник игрока.
     */
    void deleteBannedPlayer(String nickname);

    /**
     * Удаление записи об замученном игроке в базе данных.
     *
     * @param nickname Ник игрока.
     */
    void deleteMutedPlayer(String nickname);

}
