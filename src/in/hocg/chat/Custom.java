package in.hocg.chat;

import org.nutz.lang.Files;
import org.nutz.lang.util.NutMap;

import java.io.File;
import java.util.Map;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-7-9.
 */
public final class Custom {
    /**
     * session 字段容器
     */
    public interface Session {
        String USER = "user"; // 用户session key
        String ROOM = "room"; // 用户session key
    }


    public static class DirPath {
        final static String BASE = "/home/hocgin/chat_tmp/";

        public enum Flag {
            File
        }

        public static File getDir(Flag flag) { // 文件夹路径
            String dir = "";
            switch (flag) {
                case File:
                    dir = "file/";
                    break;
            }
            File dirFile = new File(String.format("%s%s", BASE, dir));
            Files.makeDir(dirFile);
            return dirFile;
        }

        public static String getDirPath(Flag flag) {
            File file = getDir(flag);
            return file.getPath();
        }
    }

    /**
     * 视图容器
     */
    public interface View {
        String Login = "login.html";
        String Chat = "chat.jsp";

        public static String JSP(String view) {
            return String.format("jsp:%s", view);
        }
    }

    /**
     * 错误码翻译器
     * @param code
     * @return
     */
    public static String _message(Integer code) {
        switch (code) {
            case 200:
                return "成功";
            case 500:
                return "失败";
            case 403:
                return "文件格式/内容不符合规定";
            case 404:
                return "查找的数据不存在";
            case 999:
            default:
                return "未知错误";
        }
    }

    public static class Data extends NutMap {
        {
            put("code", 200);
            put("message", "成功");
            put("data", null);
        }

        public Data(Integer code) {
            setCode(code);
        }

        public Data(Map<String, Object> map) {
            super(map);
        }

        public Data(String json) {
            super(json);
        }

        public Data(String key, Object value) {
            super(key, value);
        }

        public Integer getCode() {
            return getInt("code");
        }

        public Data setCode(Integer code) {
            put("code", code);
            setMessage(_message(code));
            return this;
        }

        public String getMessage() {
            return getString("message");
        }

        public Data setMessage(String message) {
            if (message != null) {
                put("message", message);
            }
            return this;
        }

        public Object getData() {
            return get("data");
        }

        public Data setData(Object data) {
            put("data", data);
            return this;
        }

        public static Data NEW(Integer code) {
            return new Data(code);
        }

        public static Data SUCCESS() {
            return new Data(200);
        }
    }

}
