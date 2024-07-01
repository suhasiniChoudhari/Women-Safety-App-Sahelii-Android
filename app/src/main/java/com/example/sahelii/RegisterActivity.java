package com.example.sahelii;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-zA-Z])" +      //any lette
                    "(?=.*[@#$%^&+=])"+     //at least one special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +                //at least 4 characters
                    "$");

    EditText edUsername, edEmail, edPassword,edAge;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegUsername);
        edEmail = findViewById(R.id.editTextRegEmail);
        edAge=findViewById(R.id.editTextRegAge);
        edPassword = findViewById(R.id.editTextRegPassword);
        btn = findViewById(R.id.buttonReg);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
    private boolean validateEmail() {
        String emailInput = edEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            edEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            edEmail.setError("Please enter a valid email address");
            return false;
        } else {
            edEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = edUsername.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            edUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            edUsername.setError("Username too long, can't be greater than 15 char");
            return false;
        } else {
            edUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = edPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            edPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            edPassword.setError("Check whether you have used at least 4 characters with at least one special character,one digit without any white spaces");
            return false;
        } else {
            edPassword.setError(null);
            return true;
        }
    }
    public void confirmInput(View v) {
        String username = edUsername.getText().toString();
        String email = edEmail.getText().toString();
        String age = edAge.getText().toString();
        String password = edPassword.getText().toString();
        Database db = new Database(getApplicationContext(), "Sahelii", null, 1);

        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            Toast.makeText(getApplicationContext(), "Record Not Inserted", Toast.LENGTH_SHORT).show();
            return;
        } else {
            db.register(username, email, age, password);
            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

        }
    }

}
