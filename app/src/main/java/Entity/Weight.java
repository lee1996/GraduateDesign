package Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leet on 18-3-16.
 */
@Entity
public class Weight {
    @Id(autoincrement = true)
    private Long _id;
    private String weight;
    private String user;
    private Long time;
    @Generated(hash = 1734804271)
    public Weight(Long _id, String weight, String user, Long time) {
        this._id = _id;
        this.weight = weight;
        this.user = user;
        this.time = time;
    }
    public Weight( String weight, String user, Long time) {
        this.weight = weight;
        this.user = user;
        this.time = time;
    }
    @Generated(hash = 1956860650)
    public Weight() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getWeight() {
        return this.weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
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
