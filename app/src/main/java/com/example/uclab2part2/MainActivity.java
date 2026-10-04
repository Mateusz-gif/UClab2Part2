package com.example.uclab2part2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int validationCode;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.name);
        EditText password = findViewById(R.id.password);
        EditText phone = findViewById(R.id.phone);
        EditText email = findViewById(R.id.email);

        Button submit = findViewById(R.id.submit);

        EditText code = findViewById(R.id.code);
        Button validate = findViewById(R.id.validate);

        submit.setOnClickListener(v -> {

            userName = name.getText().toString();
            String userPhone = phone.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            if (!userName.matches("[a-zA-Z ]+")) {
                Toast.makeText(
                        this,
                        "Name must only contain letters",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (!userPhone.matches("[0-9]+")) {
                Toast.makeText(
                        this,
                        "Telephone must only contain numbers",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (!userEmail.contains("@")) {
                Toast.makeText(
                        this,
                        "Enter a valid email",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (userPassword.isEmpty()) {
                Toast.makeText(
                        this,
                        "Password cannot be empty",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            Random random = new Random();
            validationCode = 1000 + random.nextInt(9000);

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{userEmail});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Validation Code");
            emailIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Your validation code is: " + validationCode
            );

            startActivity(emailIntent);

        });

        validate.setOnClickListener(v -> {

            String enteredCode = code.getText().toString();

            if (enteredCode.isEmpty()) {
                Toast.makeText(
                        this,
                        "Enter validation code",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (enteredCode.equals(String.valueOf(validationCode))) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", userName);
                startActivity(intent);

            } else {

                Toast.makeText(
                        this,
                        "Incorrect validation code",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

    }
}