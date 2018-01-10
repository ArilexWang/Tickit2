package com.example.ricardo.tickit2.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.ricardo.tickit2.data.entity.GDUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GDUSER".
*/
public class GDUserDao extends AbstractDao<GDUser, String> {

    public static final String TABLENAME = "GDUSER";

    /**
     * Properties of entity GDUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property NickName = new Property(1, String.class, "nickName", false, "NICK_NAME");
        public final static Property RealName = new Property(2, String.class, "realName", false, "REAL_NAME");
        public final static Property MobileNumber = new Property(3, String.class, "mobileNumber", false, "MOBILE_NUMBER");
        public final static Property Password = new Property(4, String.class, "password", false, "PASSWORD");
        public final static Property Avatar = new Property(5, String.class, "avatar", false, "AVATAR");
        public final static Property IsSuperUser = new Property(6, boolean.class, "isSuperUser", false, "IS_SUPER_USER");
        public final static Property IsAdmin = new Property(7, boolean.class, "isAdmin", false, "IS_ADMIN");
        public final static Property VipPoint = new Property(8, int.class, "vipPoint", false, "VIP_POINT");
    }


    public GDUserDao(DaoConfig config) {
        super(config);
    }
    
    public GDUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GDUSER\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"NICK_NAME\" TEXT," + // 1: nickName
                "\"REAL_NAME\" TEXT," + // 2: realName
                "\"MOBILE_NUMBER\" TEXT," + // 3: mobileNumber
                "\"PASSWORD\" TEXT," + // 4: password
                "\"AVATAR\" TEXT," + // 5: avatar
                "\"IS_SUPER_USER\" INTEGER NOT NULL ," + // 6: isSuperUser
                "\"IS_ADMIN\" INTEGER NOT NULL ," + // 7: isAdmin
                "\"VIP_POINT\" INTEGER NOT NULL );"); // 8: vipPoint
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GDUSER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GDUser entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(2, nickName);
        }
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(3, realName);
        }
 
        String mobileNumber = entity.getMobileNumber();
        if (mobileNumber != null) {
            stmt.bindString(4, mobileNumber);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(5, password);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(6, avatar);
        }
        stmt.bindLong(7, entity.getIsSuperUser() ? 1L: 0L);
        stmt.bindLong(8, entity.getIsAdmin() ? 1L: 0L);
        stmt.bindLong(9, entity.getVipPoint());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GDUser entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(2, nickName);
        }
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(3, realName);
        }
 
        String mobileNumber = entity.getMobileNumber();
        if (mobileNumber != null) {
            stmt.bindString(4, mobileNumber);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(5, password);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(6, avatar);
        }
        stmt.bindLong(7, entity.getIsSuperUser() ? 1L: 0L);
        stmt.bindLong(8, entity.getIsAdmin() ? 1L: 0L);
        stmt.bindLong(9, entity.getVipPoint());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public GDUser readEntity(Cursor cursor, int offset) {
        GDUser entity = new GDUser( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nickName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // realName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // mobileNumber
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // password
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // avatar
            cursor.getShort(offset + 6) != 0, // isSuperUser
            cursor.getShort(offset + 7) != 0, // isAdmin
            cursor.getInt(offset + 8) // vipPoint
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GDUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setNickName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRealName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMobileNumber(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPassword(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAvatar(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIsSuperUser(cursor.getShort(offset + 6) != 0);
        entity.setIsAdmin(cursor.getShort(offset + 7) != 0);
        entity.setVipPoint(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final String updateKeyAfterInsert(GDUser entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(GDUser entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GDUser entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
