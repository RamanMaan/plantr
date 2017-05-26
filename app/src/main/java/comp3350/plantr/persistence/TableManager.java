package comp3350.plantr.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Keaton MacLeod on 5/25/2017.
 *
 * This is some sample code that uses the SQLite Database built into Android Android
 * that we can use to get started on making our tables for plantr if we choose to go
 * with SQLite!
 */

public class TableManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PLANT.db"; //Sample Database name
    public static final String TABLE_NAME = "PLANT_TABLE";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DESCRIPTION";

    public TableManager(Context context) {
        //Whenever this constructor is called, the given database is created.
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase(); //Creates the database and table
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //This is an example query to create the plant table
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DESCRIPTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + TABLE_NAME);
        onCreate(db);
    }
}
