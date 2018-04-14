package Entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BLOOD_TYPE".
*/
public class BloodTypeDao extends AbstractDao<BloodType, Long> {

    public static final String TABLENAME = "BLOOD_TYPE";

    /**
     * Properties of entity BloodType.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property Bloodtype = new Property(1, String.class, "bloodtype", false, "BLOODTYPE");
        public final static Property User = new Property(2, String.class, "user", false, "USER");
        public final static Property Time = new Property(3, Long.class, "time", false, "TIME");
    }


    public BloodTypeDao(DaoConfig config) {
        super(config);
    }
    
    public BloodTypeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BLOOD_TYPE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"BLOODTYPE\" TEXT," + // 1: bloodtype
                "\"USER\" TEXT," + // 2: user
                "\"TIME\" INTEGER);"); // 3: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BLOOD_TYPE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BloodType entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String bloodtype = entity.getBloodtype();
        if (bloodtype != null) {
            stmt.bindString(2, bloodtype);
        }
 
        String user = entity.getUser();
        if (user != null) {
            stmt.bindString(3, user);
        }
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(4, time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BloodType entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String bloodtype = entity.getBloodtype();
        if (bloodtype != null) {
            stmt.bindString(2, bloodtype);
        }
 
        String user = entity.getUser();
        if (user != null) {
            stmt.bindString(3, user);
        }
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(4, time);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BloodType readEntity(Cursor cursor, int offset) {
        BloodType entity = new BloodType( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // bloodtype
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // user
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BloodType entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBloodtype(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUser(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTime(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BloodType entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BloodType entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BloodType entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}