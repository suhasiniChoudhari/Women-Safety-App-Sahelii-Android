package com.example.sahelii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterNumberActivity extends AppCompatActivity {

    TextInputEditText n1,n2,n3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_number);

        n1 = findViewById(R.id.numberEdit);
        n2 = findViewById(R.id.numberEdit1);
        n3 = findViewById(R.id.numberEdit2);

    }
     public void saveNumber(View view)
     {
                String no1 = n1.getText().toString();
                String no2 = n2.getText().toString();
                String no3 = n3.getText().toString();

              if((no1.length()==10)||(no2.length()==10)||(no3.length()==10))
              {
                  SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                  SharedPreferences.Editor myEdit = sharedPreferences.edit();
                  myEdit.putString("number1", no1);
                  myEdit.putString("number2", no2);
                  myEdit.putString("number3", no3);
                  myEdit.apply();
                  RegisterNumberActivity.this.finish();
              }
              else{
                  Toast.makeText(this, "Enter valid numbers", Toast.LENGTH_SHORT).show();
              }
       }
        }



