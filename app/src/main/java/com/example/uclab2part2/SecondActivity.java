package com.example.uclab2part2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView message = findViewById(R.id.message);

        String userName = getIntent().getStringExtra("name");

        message.setText(
                "Thank you " + userName + ", your request is being processed"
        );
    }
}