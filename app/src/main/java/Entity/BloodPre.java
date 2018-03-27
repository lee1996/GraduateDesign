package Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leet on 18-3-27.
 */
@Entity
public class BloodPre {
    @Id(autoincrement = true)
    private Long _id;
    private String shousuo;
    private String shuzhang;
    private String user;
    private Long time;
    @Generated(hash = 1548946015)
    public BloodPre(Long _id, String shousuo, String shuzhang, String user,
            Long time) {
        this._id = _id;
        this.shousuo = shousuo;
        this.shuzhang = shuzhang;
        this.user = user;
        this.time = time;
    }
    public BloodPre(String shousuo, String shuzhang, String user,
                    Long time) {
        this.shousuo = shousuo;
        this.shuzhang = shuzhang;
        this.user = user;
        this.time = time;
    }
    @Generated(hash = 1224120149)
    public BloodPre() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getShousuo() {
        return this.shousuo;
    }
    public void setShousuo(String shousuo) {
        this.shousuo = shousuo;
    }
    public String getShuzhang() {
        return this.shuzhang;
    }
    public void setShuzhang(String shuzhang) {
        this.shuzhang = shuzhang;
    }
    public String getUser() {
        return this.user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Long getTime() {
        return this.time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
    
}
