package com.voobly.ratings.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.voobly.ratings.VooblyApp;

import static com.voobly.ratings.data.local.DBContract.CREATE_TABLE_LADDERS_v1;
import static com.voobly.ratings.data.local.DBContract.CREATE_TABLE_LOBBIES_v1;
import static com.voobly.ratings.utils.Constants.DB_NAME;
import static com.voobly.ratings.utils.Constants.DB_VERSION;

/**
 * Created by jvazquez on 03/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance;

    private SQLiteDatabase db = null;

    public static DBHelper getInstance() {
        if(instance == null){
            instance = new DBHelper(VooblyApp.getInstance().getApplicationContext());
        }
        return instance;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void closeDB(){
        if(db != null && db.isOpen())
            db.close();
    }
    public SQLiteDatabase openDB(){
        if(db == null){
            db = getWritableDatabase();
        }else if(!db.isOpen()){
            db = getWritableDatabase();
        }
        return db;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOBBIES_v1());
        db.execSQL(CREATE_TABLE_LADDERS_v1());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
