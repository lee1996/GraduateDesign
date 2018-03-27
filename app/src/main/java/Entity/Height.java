package Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leet on 18-3-16.
 */
@Entity
public class Height {
    @Id(autoincrement = true)
    private Long _id;
    private String height;
    private String user;
    private Long time;
    @Generated(hash = 826249657)
    public Height(Long _id, String height, String user, Long time) {
        this._id = _id;
        this.height = height;
        this.user = user;
        this.time = time;
    }
    public Height(String height, String user, Long time){
        this.height = height;
        this.user = user;
        this.time = time;
    }
    @Generated(hash = 2023076513)
    public Height() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getHeight() {
        return this.height;
    }
    public void setHeight(String height) {
        this.height = height;
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
