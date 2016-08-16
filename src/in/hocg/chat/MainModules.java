package in.hocg.chat;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-6-29.
 */
@Modules(scanPackage = true)
@IocBy(type = ComboIocProvider.class,
        args = {
                "*js",
                    "ioc/dao.js",
                "*anno",
                    "in.hocg.chat.service",
                    "in.hocg.chat.actions",
                "*tx"
        })
@SetupBy(value = MainSetup.class) // 启动初始化数据库
public class MainModules {
}
