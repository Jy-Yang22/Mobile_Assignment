package com.example.dream_cookery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PaymentMethod extends AppCompatActivity {
    final FirebaseAuth fBaseAuth = FirebaseAuth.getInstance();
    String userID = fBaseAuth.getCurrentUser().getUid();
    ImageView imageView, imageView2, imageView3, imageView4;
    TextView mccNum, mccDate, vccNum, vccDate;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(userID).child("CC_Info");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        imageView = findViewById(R.id.ccImg);
        mccNum = findViewById(R.id.ccSavedNum);
        mccDate = findViewById(R.id.ccSavedExp);
        imageView2 = findViewById(R.id.ccImg2);
        vccNum = findViewById(R.id.ccSavedNum2);
        vccDate = findViewById(R.id.ccSavedExp2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mcNum = dataSnapshot.child("mastercard").child("ccNum").getValue(String.class);
                String mcDate = dataSnapshot.child("mastercard").child("ExpDate").getValue(String.class);
                String mcImg = dataSnapshot.child("mastercard").child("mImg").getValue(String.class);
                String vcNum = dataSnapshot.child("visa").child("ccNum").getValue(String.class);
                String vcDate = dataSnapshot.child("visa").child("ExpDate").getValue(String.class);
                String vcImg = dataSnapshot.child("visa").child("vImg").getValue(String.class);
                if(mcNum != null )
                {
                   Picasso.get().load(mcImg).into(imageView);
                    mccNum.setText("Card No. : " + mcNum);
                    mccDate.setText("Exp Date : "+ mcDate);
                }
                if(vcNum != null )
                {
                    Picasso.get().load(vcImg).into(imageView2);
                    vccNum.setText("Card No. : " + vcNum);
                    vccDate.setText("Exp Date : "+ vcDate);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void AddMaster(View view) {
        Intent master = new Intent(this, AddVisa.class);
        startActivity(master);
    }

    public void AddCC(View view) {
        Intent cc = new Intent(this, AddVisa.class);
        startActivity(cc);
    }

    public void AddVisa(View view) {
        Intent visa= new Intent(this, AddVisa.class);
        startActivity(visa);
    }
}