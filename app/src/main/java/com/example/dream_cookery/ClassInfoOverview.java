package com.example.dream_cookery;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dream_cookery.Models.Classes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class ClassInfoOverview extends AppCompatActivity {
    TextView name,id,description,insName,price;
    ImageView image;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference dataRef,databaseReference;
    FirebaseAuth mFirebaseAuth;
    private String classID = "", category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info_overview);

        classID = getIntent().getStringExtra("cID");
        category = getIntent().getStringExtra("Category");



        id=(TextView)findViewById(R.id.classInfoID);
        name=(TextView)findViewById(R.id.classInfoName);
        image=(ImageView)findViewById(R.id.classInfoImage);
        description=(TextView)findViewById(R.id.classInfoDescription);
        insName=(TextView)findViewById(R.id.classInfoInsName);
        price=(TextView)findViewById(R.id.classInfoPrice);


        getProductDetails(classID);
    }


    private void getProductDetails(String classID)
    {
        DatabaseReference classRef = FirebaseDatabase.getInstance().getReference("Classes").child(category);

        classRef.child(classID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Classes classes = dataSnapshot.getValue(Classes.class);
                    name.setText(classes.getcName());
                    description.setText(classes.getcInfoDescription());
                    Picasso.get().load(classes.getcImage()).into(image);
                    insName.setText(classes.getcInsName());
                    price.setText("RM " + classes.getcPrice() );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}