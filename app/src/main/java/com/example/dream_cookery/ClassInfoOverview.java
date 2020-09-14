package com.example.dream_cookery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
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

    Button p;
    RadioButton timeslot1,timeslot2,timeslot3,timeslot4,english,malay,chinese;
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
        p= (Button) findViewById(R.id.button_purchaseSlot);
        timeslot1 = findViewById(R.id.firstTimeSlot);
        timeslot2 = findViewById(R.id.secondTimeSlot);
        timeslot3 = findViewById(R.id.thirdTimeSlot);
        timeslot4 = findViewById(R.id.fourthTimeSlot);
        english=findViewById(R.id.firstSubtitleLanguage);
        malay=findViewById(R.id.secondSubtitleLanguage);
        chinese=findViewById(R.id.thirdSubtitleLanguage);





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
                    price.setText("RM " + classes.getcPrice() + " for 8 lessons");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
            public void onPurchaseSlot(View view){
                Intent intent=new Intent(ClassInfoOverview.this, PaymentMethod.class);
                String pri=price.getText().toString();
                String t1=timeslot1.getText().toString();
                String t2=timeslot2.getText().toString();
                String t3=timeslot3.getText().toString();
                String t4=timeslot4.getText().toString();
                String s1=english.getText().toString();
                String s2=malay.getText().toString();
                String s3=chinese.getText().toString();
                String classNm=name.getText().toString();
                intent.putExtra("price",pri);
                intent.putExtra("className",classNm);
                if(timeslot1.isChecked()) {
                    intent.putExtra("timeSlot", t1);
                }
                else if(timeslot2.isChecked()) {
                    intent.putExtra("timeSlot", t2);
                }
                else if(timeslot3.isChecked()) {
                    intent.putExtra("timeSlot", t3);
                }
                else {
                    intent.putExtra("timeSlot", t4);
                }

                if(english.isChecked()){
                    intent.putExtra("subtitle",s1);
                }
                else if(malay.isChecked()){
                    intent.putExtra("subtitle",s2);
                }
                else{
                    intent.putExtra("subtitle",s3);
                }

                startActivity(intent);
    }


}