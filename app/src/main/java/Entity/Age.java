package Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leet on 18-4-18.
 */
@Entity
public class Age {
    @Id(autoincrement = true)
    private Long _id;
    private String username;
    private int age;
    private Long time;
    public Age( int age,String username,Long time) {
        this.username = username;
        this.age = age;
        this.time=time;
    }

    @Generated(hash = 42078602)
    public Age(Long _id, String username, int age, Long time) {
        this._id = _id;
        this.username = username;
        this.age = age;
        this.time = time;
    }

    @Generated(hash = 2018356803)
    public Age() {
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
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
    
}
