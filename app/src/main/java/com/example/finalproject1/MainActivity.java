package com.example.finalproject1;

import static java.lang.Math.round;
import android.database.Cursor;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editFeet, editInch, editWeight, editTextSearch, editDate;
    Button buttonSave, buttonSearch;
    TextView textViewDisplay;
    databasehelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_main);
        editFeet = findViewById(R.id.editFeet);
        editInch = findViewById(R.id.editInch);
        editWeight = findViewById(R.id.editWeight);
        editDate = findViewById(R.id.editDate);
        buttonSave = findViewById(R.id.buttonSave);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewDisplay = findViewById(R.id.textViewDisplay);
        editTextSearch = findViewById(R.id.editTextSearch);
        dbHelper = new databasehelper(this);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = editWeight.getText().toString();
                String date = editDate.getText().toString();
                boolean added = dbHelper.insertItem(date, weight);
                if (added) {
                    Toast.makeText(MainActivity.this, "Added ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Cannot Add", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextSearch.getText().toString();
                Cursor m = dbHelper.getItemByName(title);
                if (m.moveToFirst()) {
                    String res = "Date: " + m.getString(1) + "\nWeight: " + m.getString(2 ) + " lbs";
                    textViewDisplay.setText(res);
                }
                else {
                    textViewDisplay.setText("Date not found.");
                }
                m.close();
            }
        });


    }

    @SuppressLint("DefaultLocale")
    public void onCalculateBMI(View view) {


        double feet = 0;
        double inch = 0;
        double weight = 0;

        if(!editFeet.getText().toString().equals("") && Double.parseDouble(editFeet.getText().toString()) != 0) {
            feet = Double.parseDouble(editFeet.getText().toString());
        }

        if(!editInch.getText().toString().equals("") && Double.parseDouble(editInch.getText().toString()) != 0) {
            inch = Double.parseDouble(editInch.getText().toString());
        }

        if(editInch.getText().toString().equals(" ") && Double.parseDouble(editInch.getText().toString()) == 0) {
            inch = 0;
        }

        if(!editWeight.getText().toString().equals("") && Double.parseDouble(editWeight.getText().toString()) != 0) {
            weight = Double.parseDouble(editWeight.getText().toString());
        }

        calculate calc = new calculate();
        double bMIResult = calculate.calcBMI(feet, inch, weight);
        bMIResult = Math.round(bMIResult * 100);
        bMIResult = bMIResult/100;

        String resultTxt = new String();

        if(bMIResult != 0) {
            String bmiCat = calculate.determineCat(bMIResult);
            resultTxt = "Your BMI Index is " + bMIResult + "\nBMI Category: " + bmiCat;

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("BMI");
            alert.setMessage(resultTxt);
            alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    editFeet.setText("");
                    editInch.setText("");
                    editWeight.setText("");
                }
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();


        }

        else {
            Toast.makeText(this,"Height and Weight can't be zero or less", Toast.LENGTH_SHORT).show();
        }

    }

}