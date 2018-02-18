package com.example.markxsimu.budgetxp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;



/**
 * Created by CarlosWalker on 2/18/18.
 */

public abstract class MyDBHandler extends SQLiteOpenHelper {

    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExpenseDB.db";
    public String TABLE_NAME = " ";
    public String COLUMN_TYPE = "Type ";
    public String COLUMN_EXPENSE = "Expense";
    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
    public String COLUMN_DATE = simpleDate.format(new Date());

    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create all of the tables for each kind of expense
        TABLE_NAME = "FOOD";
        String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_TYPE + "TEXT" + COLUMN_EXPENSE +
                "DOUBLE PRIMARYKEY" + COLUMN_DATE + "TEXT )";
        db.execSQL(CREATE_TABLE);

        //=================================================================================================

        TABLE_NAME = "BILLS";
        CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_TYPE + "TEXT" + COLUMN_EXPENSE +
                "DOUBLE PRIMARYKEY" + COLUMN_DATE + "TEXT )";
        db.execSQL(CREATE_TABLE);

        //=================================================================================================

        TABLE_NAME = "ACTIVITIES";
        CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_TYPE + "TEXT" + COLUMN_EXPENSE +
                "DOUBLE PRIMARYKEY" + COLUMN_DATE + "TEXT )";
        db.execSQL(CREATE_TABLE);
        //=================================================================================================

        TABLE_NAME = "GAS";
        CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_TYPE + "TEXT" + COLUMN_EXPENSE +
                "DOUBLE PRIMARYKEY" + COLUMN_DATE + "TEXT )";
        db.execSQL(CREATE_TABLE);
        //=================================================================================================

        TABLE_NAME = "CLOTHES";
        CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_TYPE + "TEXT" + COLUMN_EXPENSE +
                "DOUBLE PRIMARYKEY" + COLUMN_DATE + "TEXT )";
        db.execSQL(CREATE_TABLE);
        //=================================================================================================

        TABLE_NAME = "OTHER";
        CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_TYPE + "TEXT" + COLUMN_EXPENSE +
                "DOUBLE PRIMARYKEY" + COLUMN_DATE + "TEXT )";
        db.execSQL(CREATE_TABLE);
        //=================================================================================================

        TABLE_NAME = "BUDGET";
        CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_TYPE + "TEXT" + COLUMN_EXPENSE +
                "DOUBLE PRIMARYKEY" + COLUMN_DATE + "TEXT )";
        db.execSQL(CREATE_TABLE);

    }

    //@Override
   // public void onUpgrade(SQLiteDatabase db, int i, int j) {
   // }

    public String loadHandler(String TableName) {
        TABLE_NAME = TableName;
        String result = "";
        String query = "Select * FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            double result_1 = cursor.getDouble(1);
            String result_2 = cursor.getString(2);
            result += result_0 + " " + String.valueOf(result_1) + result_2 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(ExpenseEntry Exp, String tableName) {
        TABLE_NAME = tableName;
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, Exp.expType);
        values.put(COLUMN_EXPENSE, Double.toString(Exp.expense));
        values.put(COLUMN_DATE, Exp.date);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ExpenseEntry findHandler(String tableName, String expenseType, String type) {
        TABLE_NAME = tableName;
        COLUMN_TYPE = expenseType;
        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_TYPE + " = " + "'" + type + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ExpenseEntry exp = new ExpenseEntry();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            exp.expType = cursor.getString(0);
            exp.expense = Double.parseDouble(cursor.getString(1));
            cursor.close();
        } else {
            exp = null;
        }
        db.close();
        return exp;
    }

    public void CreatListView(String table, ArrayList<ExpenseEntry> eList) {
        ExpenseEntry exp = new ExpenseEntry();
        String result = "";
        TABLE_NAME = table;
        String query = "Select*FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            double result_1 = cursor.getDouble(1);
            String result_2 = cursor.getString(2);
            exp.date = result_2;
            exp.expense = result_1;
            exp.expType = result_0;
            eList.add(exp);
        }
    }
}

