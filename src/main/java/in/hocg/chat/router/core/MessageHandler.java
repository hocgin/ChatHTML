package in.hocg.chat.router.core;


import in.hocg.chat.router.pkg.ReceiveMessage;
import in.hocg.chat.router.pkg.ReplyMessage;

import java.io.IOException;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-7-16.
 * 消息自定义处理器
 */
public interface MessageHandler {
    ReplyMessage handle(ReceiveMessage message) throws IOException;
}
