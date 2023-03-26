package M0odiBans.M0odiBans.Database.MySQL;

import M0odiBans.M0odiBans.Database.DatabaseConstants;
import M0odiBans.M0odiBans.M0odiBans;

public class MySQL_Constants extends DatabaseConstants {

    @Override
    protected String getHost() {
        return M0odiBans.getInstance().getConfig().getString("host");
    }

    @Override
    protected String getDatabase() {
        return M0odiBans.getInstance().getConfig().getString("database");
    }

    @Override
    protected String getUser() {
        return M0odiBans.getInstance().getConfig().getString("user");
    }

    @Override
    protected String getPassword() {
        return M0odiBans.getInstance().getConfig().getString("password");
    }

}
