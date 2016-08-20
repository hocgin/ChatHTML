package in.hocg.chat.actions;

import in.hocg.chat.router.handler.DefMessageHandler;
import in.hocg.chat.router.core.MessageRouter;
import in.hocg.chat.router.enums.MessageType;
import in.hocg.chat.router.enums.ObjectsType;
import in.hocg.chat.router.pkg.ReceiveMessage;
import in.hocg.jlog.JLog;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-6-30.
 */
@ServerEndpoint(value = "/ws/{room}/{name}")
public class SocketAction {

    static int onlineCount = 0; // 总在线人数
    private static Map<String, CopyOnWriteArrayList<SocketAction>> rooms = new HashMap<>();
    private static Map<String, Integer> onlineBox = new HashMap<>();
    private Session session;
    private String name;
    private static MessageRouter router = new MessageRouter().rule()
            .type(MessageType.TEXT.name())
            .receiverType(ObjectsType.Group.name())
            .senderType(ObjectsType.Personal.name())
            .handle(new DefMessageHandler())
            .end().rule()
            .type(MessageType.IMAGE.name())
            .receiverType(ObjectsType.Group.name())
            .senderType(ObjectsType.Personal.name())
            .handle(new DefMessageHandler())
            .end().rule()
            .type(MessageType.FILE.name())
            .receiverType(ObjectsType.Group.name())
            .senderType(ObjectsType.Personal.name())
            .handle(new DefMessageHandler())
            .end().rule()
            .type(MessageType.NOTIFY.name())
            .receiverType(ObjectsType.Group.name())
            .senderType(ObjectsType.System.name())
            .handle(new DefMessageHandler())
            .end();

    @OnOpen
    public void open(@PathParam("room") String room,@PathParam("name") String name, Session session) throws IOException {
        setName(name);
        this.session = session;
        room(room).add(this);
        SocketAction.onlineCount++;
        SocketAction.addOnline(room);
        router.route(ReceiveMessage.NEW("", MessageType.NOTIFY.name(),
                room, ObjectsType.Group.name(),
                "", ObjectsType.System.name(),
                String.format("<font style=\"color: red;font-weight: bold;\">系统通知:</font> 当前房间号人数为%d，总在线人数%d", SocketAction.online(room), SocketAction.onlineCount)));
        JLog.v(String.format("当前房间号 【%s】 当前/总 【%d/%d】", room, SocketAction.online(room), SocketAction.onlineCount));
    }

    @OnError
    public void error(Session session, Throwable error) throws IOException {
        sendMessage("系统发生错误 " + error);
    }

    @OnClose
    public void close(@PathParam("room") String room) {
        SocketAction.room(room).remove(this);
        SocketAction.onlineCount--;
        SocketAction.removeOnline(room);
    }

    @OnMessage
    public void sendMessage(@PathParam("room") String room, String message, Session session) throws IOException {
        router.route(ReceiveMessage.fromJson(message));
        JLog.json("接收" + message);
    }

    /**
     * 获取聊天室
     *
     * @param no 房间号
     * @return
     */
    public static CopyOnWriteArrayList<SocketAction> room(String no) {
        CopyOnWriteArrayList<SocketAction> room = SocketAction.rooms.get(no);
        if (room == null) {
            room = new CopyOnWriteArrayList<>();
            SocketAction.rooms.put(no, room);
        }
        return room;
    }


    /**
     * 获得在线人数
     *
     * @param no 指定房间
     * @return
     */
    public static Integer online(String no) {
        Integer count = SocketAction.onlineBox.get(no);
        if (count == null) {
            SocketAction.onlineBox.put(no, 0);
        }
        return count;
    }

    /**
     * 在线人数 +1
     *
     * @param no 指定房间
     */
    public static void addOnline(String no) {
        Integer count = SocketAction.onlineBox.get(no);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        SocketAction.onlineBox.put(no, count);
    }

    /**
     * 在线人数 -1
     *
     * @param no 指定房间
     */
    public static void removeOnline(String no) {
        Integer count = SocketAction.onlineBox.get(no);
        if (count == null) {
            count = 0;
        } else {
            count--;
        }
        SocketAction.onlineBox.put(no, count);
    }

    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
