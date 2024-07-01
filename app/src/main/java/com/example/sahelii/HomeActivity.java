package com.example.sahelii;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedpreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "").toString();
        Toast.makeText(getApplicationContext(), "Welcome "+username, Toast.LENGTH_SHORT).show();

        CardView exit = findViewById(R.id.logOut);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("hasLoggedIn", false);
                editor.apply();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });

        CardView helplineNumbers = findViewById(R.id.cardHelplineNos);
        helplineNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, helplineHome.class));
            }
        });

        CardView sirenAlert = findViewById(R.id.cardSirenAlert);
        sirenAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SirenHome.class));
            }
        });

        CardView aboutUs = findViewById(R.id.cardAboutUs);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, Aboutus.class));
            }
        });

        CardView emergencySOS = findViewById(R.id.cardEmergencySOS);
        emergencySOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SOShome.class));
            }
        });

        CardView safetyTips = findViewById(R.id.cardSafetyTips);
        safetyTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SecurityMeasures.class));
            }
        });



    }
}