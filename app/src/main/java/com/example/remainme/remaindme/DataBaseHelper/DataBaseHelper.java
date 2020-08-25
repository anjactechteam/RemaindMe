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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Rck ~str~ villan on 18-Aug-20.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_REMAINDME ="appDevos_remaindMe";
    public String tableName="";
    public final String id="id";
    public final String title="title";
    public final String dateTime="dates";
    public final String time="times";
    public final String remainder="reminder";
    public final String createdAt="create_at";

    public DataBaseHelper(Context context, String table_name) {
        super(context, DB_REMAINDME, null, 1);
        this.tableName=table_name;
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+this.tableName);
        db.execSQL("create table " + this.tableName +" (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,reminder VARCHAR,dates VARCHAR,times VARCHAR,create_at DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+this.tableName);
        onCreate(db);
    }
    public boolean insertData(String title, String datetime, String reminder, String string_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.title,title);
        contentValues.put(this.dateTime,datetime);
        contentValues.put(this.remainder,reminder);
        contentValues.put(this.time,string_time);
        contentValues.put(this.createdAt,getDateTime());
        long result = db.insert(this.tableName,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+this.tableName+" ORDER BY date(dates) DESC,time(times) ASC",null);
        return res;
    }
    public Cursor getFilterData(String id){
        Cursor res=null;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] params = new String[]{id};
        res =db.query("my_task",null,"id=?",params,null,null,null);
        Log.e(">>>>>ressssssssssssssss", String.valueOf(res));
        return res;
    }
    public boolean updateData(String sid,String title,String schedule,String datetime,String sTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.title,title);
        contentValues.put(this.remainder,schedule);
        contentValues.put(this.dateTime,datetime);
        contentValues.put(this.time,sTime);
        Log.e(">>>>>data",contentValues.toString());
        db.update(this.tableName, contentValues, "id = ?",new String[] { sid });
        return true;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(this.tableName, "id = ?",new String[] {id});
    }

}
