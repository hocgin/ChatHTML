package in.hocg.chat.filters;

import in.hocg.chat.Custom;
import in.hocg.chat.utils.Generate;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.impl.processor.AbstractProcessor;

import java.io.PrintWriter;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-19.
 * 通用异常拦截器
 */
public class ExceptionFilter extends AbstractProcessor implements ActionFilter {
    @Override
    public View match(ActionContext actionContext) {
        return null;
    }

    @Override
    public void process(ActionContext actionContext) throws Throwable {
        try {
            doNext(actionContext);
        } catch (Exception e) {
            PrintWriter out = actionContext.getResponse().getWriter();
            out.print(Generate.gson().toJson(
                    Custom.Data.NEW(999).setData(e.getMessage())));
            out.close();
        }
    }
}
