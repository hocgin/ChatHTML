package in.hocg.chat.pojo;

import org.nutz.dao.entity.annotation.*;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-9.
 */
@Table("files")
public class FilesPojo extends BasePojo {

    @Name
    @Prev(els = @EL("uuid()"))
    private String id;
    @Column
    private String formerly; // 原文件名
    @Column
    private String now;      // 现文件名
    @Column
    private String keepPath; // 文件存储路径
    @Column
    private String creator;  // 上传者
    @Column
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormerly() {
        return formerly;
    }

    public void setFormerly(String formerly) {
        this.formerly = formerly;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getKeepPath() {
        return keepPath;
    }

    public void setKeepPath(String keepPath) {
        this.keepPath = keepPath;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public void status(Integer status) {
        switch (status) {
            default:
                setStatus(status);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
