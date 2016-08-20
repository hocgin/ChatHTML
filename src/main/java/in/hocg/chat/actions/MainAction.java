package in.hocg.chat.actions;

import in.hocg.chat.Custom;
import in.hocg.chat.utils.Generate;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpSession;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-13.
 */
@IocBean
public class MainAction {

    @GET
    @Ok("jsp:chat")
    @At("c/?/?")
    public void chat(String no, String user, HttpSession session) {
        String name = String.format("%s(%s)", user, Generate.generateCode(0.8, 0.5, 4, (long) (SocketAction.onlineCount + 1)));
        session.setAttribute(Custom.Session.ROOM, no);
        session.setAttribute(Custom.Session.USER, name);
    }
}
