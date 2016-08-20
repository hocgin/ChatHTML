package in.hocg.chat.router.core;


import in.hocg.chat.router.pkg.ReceiveMessage;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-7-17.
 * 消息 自定义匹配
 */
public interface MessageMatcher {
    boolean match(ReceiveMessage message);
}
