package com.example.markxsimu.budgetxp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.DatabaseMetaData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
       // dollarLoc = (EditText) findViewById(R.id.dollarLocation);

        //Add values in spinner
        Spinner spinner = (Spinner) findViewById(R.id.catSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cat_array, R.layout.cat_spinner);
        adapter.setDropDownViewResource(R.layout.cat_spinner);
        spinner.setAdapter(adapter);

        //set button collors
        Button foodButton = (Button)findViewById(R.id.FoodButton);
        Button gasButton = (Button)findViewById(R.id.GasButton);
        Button billButton = (Button)findViewById(R.id.BillButton);
        Button clothesButton = (Button)findViewById(R.id.ClothesButton);
        Button socialButton = (Button)findViewById(R.id.SocialButton);
        Button otherButton = (Button)findViewById(R.id.OtherButton);
        foodButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        gasButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        billButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        clothesButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        socialButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        otherButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));
        foodButton.setBackground(getResources().getDrawable(R.drawable.g_rectangle));





       // btnAdd =

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuOpt:
                Intent intent = new Intent(this, BillActivity.class);
                //Toast.makeText(MainActivity.this,"Share", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                //return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void FoodButton(View view){
        Intent intent = new Intent(MainActivity.this, FoodButtonActivity.class);
        startActivity(intent);
    }
}
