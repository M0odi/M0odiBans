package M0odiBans.M0odiBans.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class DateUtils {

    public static String formatDate(long dateInLong) {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Date date = new Date(dateInLong);

        return format.format(date);

    }

}
