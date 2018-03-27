package Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leet on 18-3-16.
 */
@Entity
public class LeftEye {
    @Id(autoincrement = true)
    private Long _id;
    private String lefteye;
    private String user;
    private Long time;
    @Generated(hash = 1133436802)
    public LeftEye(Long _id, String lefteye, String user, Long time) {
        this._id = _id;
        this.lefteye = lefteye;
        this.user = user;
        this.time = time;
    }
    public LeftEye( String lefteye, String user, Long time) {
        this.lefteye = lefteye;
        this.user = user;
        this.time = time;
    }
    @Generated(hash = 1096070168)
    public LeftEye() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getLefteye() {
        return this.lefteye;
    }
    public void setLefteye(String lefteye) {
        this.lefteye = lefteye;
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
