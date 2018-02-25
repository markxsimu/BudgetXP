package com.example.markxsimu.budgetxp;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;

import static java.lang.Math.round;

public class MenuOption extends AppCompatActivity {

    EditText foodAmountText,billAmountText;
    TextView totalAmountText;
    Button foodSave, billSave, menuHome;
    float totalSum=0;
    String foodBox="0";
    String billBox="0";
    final static String budgetFile = "budget_file";
    String[] budgetArray = {"0","0","0","0","0"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_option);

        foodAmountText = (EditText) findViewById(R.id.totalFoodAmount);
        totalAmountText = (TextView) findViewById(R.id.totalBudgetAmount);
        billAmountText = (EditText) findViewById(R.id.totalBillAmount);
        foodSave = (Button) findViewById(R.id.foodSave);
        billSave = (Button) findViewById(R.id.billSave);
        menuHome=(Button) findViewById(R.id.menuHome);
        foodSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodBox = foodAmountText.getText().toString();
                if(foodBox.length()!=0){
                    writeAmount(foodBox,0);
                    readAmount();
                }
                else{
                    toastMessage("ENTER A VALUE FOR FOOD");
                }
            }
        });
        billSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billBox = billAmountText.getText().toString();
                if(billBox.length()!=0){
                    writeAmount(billBox,1);
                    readAmount();
                }
                else{
                    toastMessage("ENTER A VALUE FOR BILL");
                }
            }
        });
        menuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(MenuOption.this,MainActivity.class);
                startActivity(home);
            }
        });

        //deleteFile();
        readAmount();

    }
    public void writeAmount(String amount, int place){
        try {
            File dir = getFilesDir();
            File findFile = new File(dir,budgetFile);
            if(findFile.exists()){
                deleteFile();
            }

            FileOutputStream fileOutputStream = openFileOutput(budgetFile,MODE_PRIVATE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            budgetArray[place]=amount;
            for(int counter = 0; counter<budgetArray.length;counter++) {
                fileOutputStream.write((budgetArray[counter]+"\n").getBytes());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileOutputStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void readAmount(){
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput(budgetFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            int counter = 0;
            totalSum = 0;
            while((Message=bufferedReader.readLine())!=null && counter<5){
                //stringBuffer.append(Message +"\n");
                budgetArray[counter]=Message.toString();
                Log.v(Integer.toString(counter),budgetArray[counter]);
                totalSum += Float.parseFloat(budgetArray[counter]);
                counter++;
            }
            assignvalue();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void assignvalue(){
        String sum =String.format("%.2f",totalSum);
        totalAmountText.setText("$"+sum);
        String foodsum = String.format("%.2f",Double.parseDouble(budgetArray[0]));
        String billsum = String.format("%.2f",Double.parseDouble(budgetArray[1]));
        foodAmountText.setText("$"+foodsum);
        billAmountText.setText("$"+billsum);
    }
    private void deleteFile(){
        File dir = getFilesDir();
        File file = new File(dir,budgetFile);
        boolean deleted = file.delete();
        toastMessage("Files deleted");

    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
