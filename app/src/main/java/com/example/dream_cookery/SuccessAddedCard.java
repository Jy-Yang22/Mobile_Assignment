package com.example.dream_cookery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuccessAddedCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_added_card);
    }

    public void paymentActivity(View view) {
        Intent payment= new Intent(this, PaymentMethod.class);
        startActivity(payment);
    }
}