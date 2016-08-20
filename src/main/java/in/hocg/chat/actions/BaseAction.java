package in.hocg.chat.actions;


import in.hocg.chat.Custom;
import in.hocg.chat.service.BaseService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;


/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-7-9.
 */
@IocBean
public abstract class BaseAction<T extends BaseService> {

    @Inject
    private T service;
    public T service(){
        return service;
    }

    public Custom.Data success(Object data) {
        return Custom.Data.SUCCESS().setData(data);
    }

    public Custom.Data success() {
        return Custom.Data.SUCCESS();
    }

    public Custom.Data result(int code, Object data) {
        return Custom.Data.NEW(code).setData(data);
    }

}
