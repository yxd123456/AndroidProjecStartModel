package asus.exercise01.base;

import android.app.Application;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by asus on 2016/7/25.
 */
public class MainApplication extends Application {

    private static DbManager.DaoConfig config;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        config = new DbManager.DaoConfig()
                .setDbName("ddd.db")
                .setTableCreateListener((db, table)->
                        Log.d("Test", "onCreate: "+table.getName()))
//                .setAllowTransaction(true)
                .setDbDir(new File
                        (Environment.getExternalStorageDirectory().getPath()+
                                File.separator+"Test"+File.separator))
//                .setDbVersion(1)
                .setDbUpgradeListener((db, oldVersion, newVersion) -> {

                }).setDbOpenListener(db -> {
                    db.getDatabase().enableWriteAheadLogging();
                });
    }

    public static DbManager.DaoConfig getConfig(){
        return config;
    }
}
