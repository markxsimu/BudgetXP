package com.example.markxsimu.budgetxp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.DatabaseMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.lang.String;


public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button btnAdd;
    //private Button btnViewFood;
    public Button btnViewBill;
    public Button btnViewGas;
    public Button btnViewOther;
    public Spinner cat;
    public int sel = 5;



    DataBaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        mDatabaseHelper = new DataBaseHelper(this);
        // dollarLoc = (EditText) findViewById(R.id.dollarLocation);


        //Add values in spinner
        Spinner spinner = (Spinner) findViewById(R.id.catSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cat_array, R.layout.cat_spinner);
        adapter.setDropDownViewResource(R.layout.cat_spinner);
        spinner.setAdapter(adapter);


        cat = (Spinner) findViewById(R.id.catSpinner);
        final EditText editAm = findViewById(R.id.expenseAmount);


        btnAdd = findViewById(R.id.button_submit);
        //btnViewFood =  findViewById(R.id.FoodButton);
        btnViewBill = findViewById(R.id.BillButton);
        btnViewOther = findViewById(R.id.OtherButton);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String newEntry = editAm.getText().toString();
                if (editAm.length() != 0) {
                    AddData(newEntry, sel);
                    editAm.setText(newEntry);
                    toastMessage("added the data");
                    }
                else {
                       toastMessage("Must Enter an Input");
                     }
                }
        });


        //set button colors
        Button foodButton = findViewById(R.id.FoodButton);
        Button clothesButton = findViewById(R.id.ClothesButton);
        Button socialButton = findViewById(R.id.SocialButton);
        foodButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        btnViewGas.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        btnViewBill .setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        clothesButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        socialButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        btnViewOther.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        foodButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
    }
    public void GetSpinItem(int num) {

                num= cat.getSelectedItemPosition();
                 sel = num;
    }

    public void AddData(String newEntry, int sel) { //boolean insertData = mDatabaseHelper.addData(newEntry,"02/16/2018");
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        mDatabaseHelper.addData(newEntry);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void FoodButton(View view) {
        Intent intent = new Intent(MainActivity.this, FoodButtonActivity.class);
        startActivity(intent);
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
