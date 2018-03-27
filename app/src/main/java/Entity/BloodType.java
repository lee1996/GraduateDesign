package Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leet on 18-3-16.
 */
@Entity
public class BloodType {
    @Id(autoincrement = true)
    private Long _id;
    private String bloodtype;
    private String user;
    private Long time;
    @Generated(hash = 724433356)
    public BloodType(Long _id, String bloodtype, String user, Long time) {
        this._id = _id;
        this.bloodtype = bloodtype;
        this.user = user;
        this.time = time;
    }
    public BloodType(String bloodtype, String user, Long time) {
        this.bloodtype = bloodtype;
        this.user = user;
        this.time = time;
    }
    @Generated(hash = 773925129)
    public BloodType() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getBloodtype() {
        return this.bloodtype;
    }
    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
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
