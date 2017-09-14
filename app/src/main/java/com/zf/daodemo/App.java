package com.zf.daodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.UserDao;
import com.facebook.stetho.Stetho;

/**
 * Created by liyiwei
 * on 2017/9/14.
 */

public class App extends Application {

    public static UserDao mUserDao;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users-db", null);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        DaoSession daoSession = daoMaster.newSession();
        mUserDao = daoSession.getUserDao();
    }
}
