package in.hocg.chat;

import in.hocg.chat.filters.ExceptionFilter;
import in.hocg.chat.filters.JumpFilter;
import org.nutz.mvc.annotation.*;
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
@Filters({@By(type = ExceptionFilter.class), @By(type = JumpFilter.class)})
public class MainModules {
}
