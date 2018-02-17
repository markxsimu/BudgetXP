package com.example.markxsimu.budgetxp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toolbar;

/**
 * Created by markxsimu on 2/17/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private  static final String TAG = "DatabaseHelper";

    private  static final String TABLE_NAME = "expense_table";
    private  static final String COL1 = "ID";
    private  static final String COL2 = "name";



    public DataBaseHelper(Context context) {
        super(context, TABLE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE" + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       // db.execSQL("DROP IF TABLE EXIST" +TABLE_NAME );
        onCreate(db);
    }
    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        return false;
    }


}
