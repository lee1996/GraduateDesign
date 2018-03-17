package Application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import Entity.DaoMaster;
import Entity.DaoSession;

/**
 * Created by leet on 18-3-17.
 */

public class MyApplication extends Application {
    public static DaoSession daoSession = null;
    public static MyApplication instance;
    public static Context context;
    public static SQLiteDatabase db = null;
    public static DaoMaster.DevOpenHelper helper = null;
    public static DaoMaster daoMaster = null;
    private String TAG = MyApplication.class.getSimpleName();

    public static Context getContext() {
        return context;
    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = getHelperInstance(this);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        if (daoMaster == null) {
            daoMaster = new DaoMaster(db);
        }
        if (daoSession == null) {
            daoSession = daoMaster.newSession();
        }
    }

    public synchronized DaoMaster.DevOpenHelper getHelperInstance(Context context) {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, "greendao.db", null);
        }
        return helper;
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            setupDatabase();
        }
        return db;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
        context=getApplicationContext();
    }
}
