package in.hocg.chat.utils;

import com.google.gson.Gson;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-7-9.
 * 生成器, 每个方法都拥有一个返回值
 */
public class Generate {


    /**
     * 号码生成器
     * @param base 限制起点
     * @param index
     * @return 最长long型数据数据长度
     */
    public synchronized static String numberCode(long base, long index) {
        return String.valueOf(base + index);
    }

    /**
     * 给密码 加盐
     *
     * @param pw
     * @return
     */
    public static String _joinSalt(String pw) {
        return String.format("%s#%s", pw, "hocg.in");
    }

    private static Gson GSON;
    public static Gson gson() {
        if (GSON == null) {
            synchronized (Generate.class) {
                if (GSON == null) {
                    return GSON = new Gson();
                }
            }
        }
        return GSON;
    }


    /**
     * 生成邀请码
     * @param numberProbability 整数概率
     * @param upProbability 大写字母概率
     * @param bt 位数
     * @param index 用户编号
     * @return
     */
    public static String generateCode(double numberProbability, double upProbability, int bt, Long index) {
        double base = Math.pow(10, bt - 1);
        String code = "";
        for (char c : String.valueOf((long) base + index).toCharArray()) {
            String s = String.valueOf(c);
            code += Math.random() > numberProbability ?
                    ((char) (Integer.valueOf(s) + (Math.random() > upProbability ? 97 : 65))) : s;
        }
        System.out.println(String.format("数字/字母概率(%f/%f), 第 %-3s 用户, 邀请码(%d)是 %s",
                numberProbability, upProbability,
                index, bt, code));
        return code;
    }
}
