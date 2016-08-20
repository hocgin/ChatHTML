package in.hocg.chat.pojo;

import in.hocg.chat.pojo.enums.DefStatus;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-6-29.
 */
public abstract class BasePojo implements Serializable {
    @Column("createTime")
    protected Timestamp createTime;
    @Column("updateTime")
    protected Timestamp updateTime;
    @Column
    @Default("1")
    protected Integer status;

    public String toString() {
        return String.format("/*%s*/%s", super.toString(), Json.toJson(this, JsonFormat.compact()));
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public abstract void status(Integer status) ;

    protected void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    private void disable() {
        setStatus(DefStatus.Disable.getCode());
    }

    private void open() {
        setStatus(DefStatus.Open.getCode());
    }
}
