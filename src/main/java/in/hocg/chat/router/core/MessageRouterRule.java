package in.hocg.chat.router.core;


import in.hocg.chat.router.pkg.ReceiveMessage;
import in.hocg.chat.router.pkg.ReplyMessage;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-7-16.
 * 消息路由规则
 */
public class MessageRouterRule {

    private final MessageRouter router;
    private List<MessageHandler> handlers = new ArrayList<>();
    private List<MessageInterceptor> interceptors = new ArrayList<>();
    private boolean async = true;
    private boolean reEnter = false;
    private MessageMatcher matcher;

    // 校验字段 --
    private String event;
    private String type;
    private String receiver;
    private String receiverType;
    private String senderType;
    private String sender;
    private String rContent;
    // -- 校验字段


    MessageRouterRule(MessageRouter router) {
        this.router = router;
    }


    /**
     * 如果消息匹配某个matcher，用在用户需要自定义更复杂的匹配规则的时候
     *
     * @param matcher
     * @return
     */
    public MessageRouterRule matcher(MessageMatcher matcher) {
        this.matcher = matcher;
        return this;
    }

    public MessageRouterRule async(boolean isAsync) {
        setAsync(isAsync);
        return this;
    }

    public MessageRouterRule handle(MessageHandler handler, MessageHandler... otherHandler) {
        this.handlers.add(handler);
        if (otherHandler != null && otherHandler.length > 0) {
            Collections.addAll(handlers, otherHandler);
        }
        return this;
    }

    public MessageRouter end() {
        router.getRules().add(this);
        return router;
    }

    public MessageRouter next() {
        setReEnter(true);
        return end();
    }

    /**
     * 消息处理
     * @param message
     * @return
     */
    protected ReplyMessage service(ReceiveMessage message) {

        Map<String, Object> context = new HashMap<>();
        for (MessageInterceptor interceptor : interceptors) { // 消息拦截器
            if (!interceptor.intercept(message, context)) {
                return null;
            }
        }

        ReplyMessage re = null;
        for (MessageHandler handler : handlers) { // 消息处理器
            try {
                re = handler.handle(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return re;
    }

    public MessageRouterRule interceptor(MessageInterceptor interceptor) {
        return interceptor(interceptor, (MessageInterceptor[]) null);
    }

    /**
     * 添加消息拦截器
     * @param interceptor
     * @param otherInterceptor
     * @return
     */
    public MessageRouterRule interceptor(MessageInterceptor interceptor, MessageInterceptor... otherInterceptor) {
        interceptors.add(interceptor);
        if (otherInterceptor != null && otherInterceptor.length > 0) {
            Collections.addAll(interceptors, otherInterceptor);
        }
        return this;
    }

    /**
     * 匹配规则区
     * @param message
     * @return
     */
    protected boolean test(ReceiveMessage message) {
        return (this.sender == null || this.sender.equals(message.getSender()))
                && (this.receiver == null || this.receiver.equals(message.getReceiver()))
                && (this.type == null || this.type.equals(message.getType()))
                && (this.receiverType == null || this.receiverType.equals(message.getReceiverType()))
                && (this.senderType == null || this.senderType.equals(message.getSenderType()))
                && (this.event == null || this.event.equals(message.getEvent()))
                && (this.rContent == null || Pattern.matches(this.rContent, message.getContent() == null? "": message.getContent().trim()))
                && (this.matcher == null || matcher.match(message))
                ;
    }

    public boolean isAsync() {
        return async;
    }

    private void setAsync(boolean async) {
        this.async = async;
    }

    public boolean isReEnter() {
        return reEnter;
    }

    private void setReEnter(boolean reEnter) {
        this.reEnter = reEnter;
    }

    /**
     * 内容匹配
     * @return
     */
    public MessageRouterRule rContent(String rContent) {
        this.rContent = rContent;
        return this;
    }

    /**
     * 等值匹配
     * @return
     */
    public MessageRouterRule sender(String sender) {
        this.sender = sender;
        return this;
    }

    /**
     * 等值匹配
     * @return
     */
    public MessageRouterRule receiverType(String receiverType) {
        this.receiverType = receiverType;
        return this;
    }
    /**
     * 等值匹配
     * @return
     */
    public MessageRouterRule senderType(String senderType) {
        this.senderType = senderType;
        return this;
    }


    /**
     * 等值匹配
     * @return
     */
    public MessageRouterRule receiver(String receiver) {
        this.receiver = receiver;
        return this;
    }


    /**
     * 等值匹配
     * @return
     */
    public MessageRouterRule type(String type) {
        this.type = type;
        return this;
    }

    /**
     * 等值匹配
     * @param event
     * @return
     */
    public MessageRouterRule event(String event) {
        this.event = event;
        return this;
    }
}
