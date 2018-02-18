package com.example.markxsimu.budgetxp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toolbar;

/**
 * Created by markxsimu on 2/17/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private  static final String TAG = "DatabaseHelper";

    private  static final String TABLE_NAME = "expense_table";
    private  static final String COL1 = "ID";
    private  static final String COL2 = "name";
    private  static final String DROP_TABLE = "DROP IF TABLE EXISTS";



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
        db.execSQL(DROP_TABLE+" "+TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        Log.d("TAG","addData: Adding "+ item + " to " + TABLE_NAME);
        long result =db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;
        }else{return true;}
    }




    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+ TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + "FROM "+ TABLE_NAME + " WHERE "+COL2 + " = '"+name +"'";
        Cursor data =db.rawQuery(query, null);
        return data;
    }
}
