package Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leet on 18-4-18.
 */
@Entity
public class Gender {
    @Id(autoincrement = true)
    private Long _id;
    private String username;
    private String gender;

    public Gender(String username, String gender) {
        this.username = username;
        this.gender = gender;
    }

    @Generated(hash = 1106921146)
    public Gender(Long _id, String username, String gender) {
        this._id = _id;
        this.username = username;
        this.gender = gender;
    }
    @Generated(hash = 2034361717)
    public Gender() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
