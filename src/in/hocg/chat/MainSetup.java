package in.hocg.chat;

import in.hocg.jlog.JLog;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-6-29.
 */
public class MainSetup implements Setup {
    @Override
    public void init(NutConfig nutConfig) {
        JLog.init("hocg.in");

        Ioc ioc = nutConfig.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "in.hocg.chat", false);
    }

    @Override
    public void destroy(NutConfig nutConfig) {

    }
}
