package in.hocg.chat.handler;

import in.hocg.chat.actions.SocketAction;
import in.hocg.chat.router.core.MessageHandler;
import in.hocg.chat.router.pkg.ReceiveMessage;
import in.hocg.chat.router.pkg.ReplyMessage;

import java.io.IOException;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-9.
 */
public class DefMessageHandler implements MessageHandler {
    @Override
    public ReplyMessage handle(ReceiveMessage message) {
        ReplyMessage replyMessage = new ReplyMessage();
        replyMessage.setSenderType(message.getReceiverType());
        replyMessage.setSender(message.getReceiver());
        replyMessage.setContent(message.getContent());
        replyMessage.setEvent(message.getEvent());
        replyMessage.setTime(System.currentTimeMillis());
        replyMessage.setType(message.getType());
        replyMessage.setReceiverType(message.getSenderType());
        replyMessage.setReceiver(message.getSender());
        for (SocketAction socket : SocketAction.room(message.getReceiver())) { // 发送
            try {
                socket.sendMessage(replyMessage.toJson());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return replyMessage;
    }
}
