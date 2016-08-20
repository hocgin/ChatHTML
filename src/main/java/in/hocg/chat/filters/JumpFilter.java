package in.hocg.chat.filters;

import in.hocg.chat.Custom;
import in.hocg.jlog.util.LangKit;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.impl.processor.AbstractProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-19.
 * 自动跳转拦截器
 */
public class JumpFilter extends AbstractProcessor implements ActionFilter {
    @Override
    public View match(ActionContext actionContext) {
        return null;
    }

    @Override
    public void process(ActionContext actionContext) throws Throwable {
        HttpServletRequest request = actionContext.getRequest();
        String url = request.getParameter(Custom.Params.JUMP);
        if (LangKit.isEmpty(url)) {
            doNext(actionContext);
        } else {
            HttpServletResponse response = actionContext.getResponse();
            response.sendRedirect(url);
        }
    }
}
