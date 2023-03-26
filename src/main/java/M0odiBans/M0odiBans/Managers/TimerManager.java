package M0odiBans.M0odiBans.Managers;

import M0odiBans.M0odiBans.M0odiBans;
import org.bukkit.scheduler.BukkitRunnable;

public interface TimerManager {

    default void startTimer(int delay, int period) {

        new BukkitRunnable() {

            @Override
            public void run() {
                executeTimer();
            }

        }.runTaskTimer(M0odiBans.getInstance(), delay, period);

    }

    void executeTimer();

}
