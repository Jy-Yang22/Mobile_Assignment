package com.example.dream_cookery;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class ClassInfoOverview extends AppCompatActivity {
    TextView name,id,description,insName;
    ImageView image;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference dataRef,databaseReference;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info_overview);

        mFirebaseAuth= FirebaseAuth.getInstance();
        id=(TextView)findViewById(R.id.classInfoID);
        name=(TextView)findViewById(R.id.classInfoName);
        image=(ImageView)findViewById(R.id.classInfoImage);
        description=(TextView)findViewById(R.id.classInfoDescription);
        insName=(TextView)findViewById(R.id.classInfoInsName);
        String classid = getIntent().getStringExtra("id");
        databaseReference= firebaseDatabase.getReference("C1");
        dataRef =databaseReference.child(classid);
        id.setText(classid);

    }
    @Override
    public void onStart() {
        super.onStart();
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nm = dataSnapshot.child("cInfoName").getValue(String.class);
                String img = dataSnapshot.child("cInfoImage").getValue(String.class);
                String dsc = dataSnapshot.child("cInfoDescription").getValue(String.class);
                String insNm = dataSnapshot.child("cInfoInsName").getValue(String.class);
                name.setText(nm);
                Picasso.get().load(img).into(image);
                description.setText(dsc);
                insName.setText(insNm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





}