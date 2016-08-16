package in.hocg.chat.router.core;


import in.hocg.chat.router.pkg.ReceiveMessage;

import java.util.Map;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-7-17.
 * 消息拦截器-接口
 */
public interface MessageInterceptor {
    boolean intercept(ReceiveMessage message, Map<String, Object> context);
}
