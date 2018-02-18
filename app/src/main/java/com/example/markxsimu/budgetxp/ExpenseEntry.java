package com.example.markxsimu.budgetxp;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;


import android.icu.text.DateFormat;
import android.text.format.Time;

/**
 * Created by CarlosWalker on 2/18/18.
 */

public class ExpenseEntry {
    // fields-----------------------------------------------------
    public double expense = 0.00;
    public String info = " ";
    public String foodStr = "Food";
    public String billStr = "Bills";
    public String actStr = "Activity";
    public String gasStr = "Gas";
    public String clothesStr = "Clothes";
    public String otherStr = "Other";
    public String expType = " ";
    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
    public String date = simpleDate.format(new Date());


    // Constructors----------------------------------------
    public ExpenseEntry() {}
    public ExpenseEntry(double exp, String type, String date) {
        this.expense = exp;
        this.expType = type;
        this.date = date;
    }
}
