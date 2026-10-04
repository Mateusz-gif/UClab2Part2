package com.example.uclab2part2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name= findViewById(R.id.name);
        EditText password= findViewById(R.id.password);
        EditText phone= findViewById(R.id.phone);
        EditText email= findViewById(R.id.email);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(v -> {

            String userName = name.getText().toString();
            String userPhone = phone.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            if(!userName.matches("[a-zA-Z ]+")){
                Toast.makeText(
                        this,
                        "Name must only contain letters",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if(!userPhone.matches("[0-9]+")){
                Toast.makeText(
                        this,
                        "Telephone must only contain numbers",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if(!userEmail.contains("@")){
                Toast.makeText(this,
                        "Enter a valid email",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if(userPassword.isEmpty()){
                Toast.makeText(this,
                        "Password cannot be empty",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("name", userName);
            startActivity(intent);
        });

    }
}