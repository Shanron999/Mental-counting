package com.example.projetcalculemental.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.projetcalculemental.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        associateOpenActivityTobutton(R.id.buttonplay, DifficultesActivity.class);
        associateOpenActivityTobutton(R.id.buttonscore,ScoreActivity.class);
    }
    private void associateOpenActivityTobutton(int id,Class activity)
    {
        Button button = findViewById(id);
        button.setOnClickListener(view -> openActivity(activity));
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }
}