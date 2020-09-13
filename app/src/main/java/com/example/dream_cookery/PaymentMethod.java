package com.example.dream_cookery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PaymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
    }

    public void AddMaster(View view) {
        Intent master = new Intent(this, AddMaster.class);
        startActivity(master);
    }

    public void AddVisa(View view) {
        Intent visa= new Intent(this, AddVisa.class);
        startActivity(visa);
    }
}