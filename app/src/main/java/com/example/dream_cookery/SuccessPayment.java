package com.example.dream_cookery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dream_cookery.Models.History;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuccessPayment extends AppCompatActivity {

    DatabaseReference HistoryRef;
    FirebaseAuth firebaseAuth;
    String currentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_payment);


        firebaseAuth = FirebaseAuth.getInstance();
        currentID = firebaseAuth.getCurrentUser().getUid();

        HistoryRef = FirebaseDatabase.getInstance().getReference("Users").child(currentID);
        HistoryRef.child("cart").removeValue();
    }

    public void backhome(View view)
    {
        Intent home = new Intent(this, MainClassView.class);
        startActivity(home);
    }

}