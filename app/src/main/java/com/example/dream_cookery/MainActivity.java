package com.example.dream_cookery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void helloWorld(View view) {
        Toast.makeText(this, "Hello World!", Toast.LENGTH_SHORT).show();
        openMainClassView();
    }

    public void openMainClassView() {
        Intent intent = new Intent(this, MainClassView.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}