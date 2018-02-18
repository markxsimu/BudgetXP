package com.example.markxsimu.budgetxp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by markxsimu on 2/17/18.
 */

public class EditDataActivity extends AppCompatActivity {
    private static final String TAG = "EditDataActivity";

    private Button btnSave, btnDelete;
    private EditText editable_item;
    DataBaseHelper mDatabaseHelper;
    private String selectedName;
    private int selectedID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);
        btnSave = (Button) findViewById(R.id.saveData);
        btnDelete = (Button) findViewById(R.id.deleteData);
        editable_item = (EditText)findViewById(R.id.editData);
        mDatabaseHelper =new DataBaseHelper(this);

        //get intent extra from listDataActivity
        Intent recievedIntent = getIntent();

        //now get the item id we passed as an extra
        selectedID = recievedIntent.getIntExtra("id",-1);

        //now we get the name we passed as an extra
        selectedName = recievedIntent.getStringExtra("name");

        //set the text to show current selected name
        editable_item.setText(selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("removed from database");
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
