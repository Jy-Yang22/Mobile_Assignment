package com.example.dream_cookery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuccessPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_payment);
    }

    public void backhome(View view)
    {
        Intent home = new Intent(this, MainClassView.class);
        startActivity(home);
    }

}