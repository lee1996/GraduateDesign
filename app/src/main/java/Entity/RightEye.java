package Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leet on 18-3-16.
 */
@Entity
public class RightEye {
    @Id(autoincrement = true)
    private Long _id;
    private String righteye;
    private String user;
    private Long time;
    @Generated(hash = 941362958)
    public RightEye(Long _id, String righteye, String user, Long time) {
        this._id = _id;
        this.righteye = righteye;
        this.user = user;
        this.time = time;
    }
    public RightEye(String righteye, String user, Long time) {
        this.righteye = righteye;
        this.user = user;
        this.time = time;
    }
    @Generated(hash = 908200717)
    public RightEye() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getRighteye() {
        return this.righteye;
    }
    public void setRighteye(String righteye) {
        this.righteye = righteye;
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
