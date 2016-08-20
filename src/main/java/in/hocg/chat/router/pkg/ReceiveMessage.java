package in.hocg.chat.router.pkg;


import in.hocg.chat.utils.Generate;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-7-17.
 * 接收的消息
 */
public class ReceiveMessage {
    /**
     * id :
     * event :
     * type :
     * to :
     * toType :
     * from :
     * content :
     * time : 1467382586
     */

    private String id;
    private String event;
    private String type;
    private String receiver;
    private String receiverType;
    private String senderType;
    private String sender;
    private String content;
    private Long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public static ReceiveMessage fromJson(String json) {
        return Generate.gson().fromJson(json, ReceiveMessage.class);
    }

    public static ReceiveMessage NEW(
            String event,
            String type,
            String receiver,
            String receiverType,
            String sender,
            String senderType,
            String content
    ) {
        ReceiveMessage receiveMessage = new ReceiveMessage();
        receiveMessage.setEvent(event);
        receiveMessage.setType(type);
        receiveMessage.setReceiver(receiver);
        receiveMessage.setReceiverType(receiverType);
        receiveMessage.setSender(sender);
        receiveMessage.setSenderType(senderType);
        receiveMessage.setContent(content);
        receiveMessage.setTime(System.currentTimeMillis());
        return receiveMessage;
    }
}
