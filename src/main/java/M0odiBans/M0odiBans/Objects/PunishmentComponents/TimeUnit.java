package M0odiBans.M0odiBans.Objects.PunishmentComponents;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TimeUnit {

    MINUTE ("MINUTE", 1), HOUR ("HOUR", 60),
    DAY ("DAY", 60 * 24), WEEK ("WEEK", 60*24*7),
    MONTH ("MONTH", 30*60*24), YEAR ("YEAR", 30*60*24*12);

    private final String unitName;

    private final long multiply;

    public static long getFinalPunishmentTime(String unit, long time) {

        for (TimeUnit units : TimeUnit.values()) {
            if (units.unitName.equalsIgnoreCase(unit)) return time * 60L * units.multiply * 1000L;
        }

        return 0L;

    }

}
