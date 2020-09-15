package com.example.dream_cookery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dream_cookery.Models.Classes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class profilePageView extends AppCompatActivity {

    private TextView nameText;
    FirebaseAuth fBaseAuth;
    String currentID, userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fBaseAuth = FirebaseAuth.getInstance();
        currentID = fBaseAuth.getCurrentUser().getUid();
        nameText = findViewById(R.id.customerName);





    }

    private void userInfoDisplay() {
        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        UsersRef.child(currentID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {

                    userName = dataSnapshot.child("username").getValue(String.class);
                    Log.d("hello", userName);
                    nameText.setText(userName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        userInfoDisplay();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }
    public void onGroupItemClick(MenuItem item) {
    }

    public void goToProfileDetail(View arg0) {
        Intent it1 = new Intent(getApplicationContext(), editProfileView.class);
        startActivity(it1);
    }

    public void goToPurchaseHistory(View arg0) {
        Intent it2 = new Intent(getApplicationContext(), purchaseHistoryView.class);
        startActivity(it2);
    }

    public void goToAboutUs(View arg0) {
        Intent it3 = new Intent(getApplicationContext(), aboutUsView.class);
        startActivity(it3);
    }

    public void paymentmethod(View view)
    {
        Intent payment = new Intent(this, PaymentMethod.class);
        startActivity(payment);
    }

}


