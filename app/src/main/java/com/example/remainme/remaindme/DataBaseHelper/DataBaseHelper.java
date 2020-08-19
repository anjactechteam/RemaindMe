package com.example.remainme.remaindme.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Rck ~str~ villan on 18-Aug-20.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_REMAINDME ="appDevos_remaindMe";
    public String tableName="";
    public final String id="id";
    public final String title="title";
    public final String dateTime="date";
//    public final String time="time";
    public final String remainder="reminder";
    public final String createdAt="create_at";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public DataBaseHelper(Context context, String table_name) {
        super(context, DB_REMAINDME, null, 1);
        this.tableName=table_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+this.tableName);
        db.execSQL("create table " + this.tableName +" (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,reminder VARCHAR,date VARCHAR,create_at DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+this.tableName);
        onCreate(db);
    }
    public boolean insertData(String title,String datetime,String reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.title,title);
        contentValues.put(this.dateTime,datetime);
        contentValues.put(this.remainder,reminder);
        contentValues.put(this.createdAt,dtf.format(now));
        Log.e(">>>>>data",contentValues.toString());
        long result = db.insert(this.tableName,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+this.tableName,null);
        return res;
    }
}
