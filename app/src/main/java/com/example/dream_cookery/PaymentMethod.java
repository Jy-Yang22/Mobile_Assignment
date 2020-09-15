package com.example.dream_cookery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    String cClass,cSub,cTime,cPri;
    ImageView imageView, imageView2, imageView3, imageView4;
    TextView mccNum, mccDate, vccNum, vccDate, cClassName, cSubtitle, cTimeSlot, cPrice;
    LinearLayout list1,list2,list3;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(userID);
    int total;
    String price, subtitle, timeslot, className;
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
        price = getIntent().getStringExtra("price");
        subtitle = getIntent().getStringExtra("subtitle");
        timeslot = getIntent().getStringExtra("timeSlot");
        className = getIntent().getStringExtra("className");
        list1 = findViewById(R.id.cclist1);
        list2 = findViewById(R.id.cclist2);
        list3 = findViewById(R.id.list3);
        cClassName = findViewById(R.id.cartclass);
        cSubtitle = findViewById(R.id.cartsub);
        cPrice = findViewById(R.id.cartprice);
        cTimeSlot = findViewById(R.id.carttime);

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total = dataSnapshot.child("total").getValue(int.class);
                total++;
                String mcNum = dataSnapshot.child("CC_Info").child("mastercard").child("ccNum").getValue(String.class);
                String mcDate = dataSnapshot.child("CC_Info").child("mastercard").child("ExpDate").getValue(String.class);

                String vcNum = dataSnapshot.child("CC_Info").child("visa").child("ccNum").getValue(String.class);
                String vcDate = dataSnapshot.child("CC_Info").child("visa").child("ExpDate").getValue(String.class);

                if(className!=null)
                {
                    databaseReference.child("cart").child("classname").setValue(className);
                    databaseReference.child("cart").child("subtitle").setValue(subtitle);
                    databaseReference.child("cart").child("timeslot").setValue(timeslot);
                    databaseReference.child("cart").child("price").setValue(price);
                }

                cClass = dataSnapshot.child("cart").child("classname").getValue(String.class);
                cSub = dataSnapshot.child("cart").child("subtitle").getValue(String.class);
                cTime = dataSnapshot.child("cart").child("timeslot").getValue(String.class);
                cPri = dataSnapshot.child("cart").child("price").getValue(String.class);

                if(cClass!=null)
                {
                    list1.setVisibility(View.VISIBLE);
                    cClassName.setText("Class : " + cClass);
                    cSubtitle.setText("Subtitle : "+ cSub);
                    cPrice.setText("Price : " + cPri);
                    cTimeSlot.setText("Timeslot : "+ cTime);

                }
                if(mcNum != null )
                {
                    list1.setVisibility(View.VISIBLE);
                    mccNum.setText("Card No. : " + mcNum);
                    mccDate.setText("Exp Date : "+ mcDate);
                }
                else
                    list1.setVisibility(View.GONE);

                if(vcNum != null )
                {
                    list2.setVisibility(View.VISIBLE);
                    vccNum.setText("Card No. : " + vcNum);
                    vccDate.setText("Exp Date : "+ vcDate);
                }
                else
                    list2.setVisibility(View.GONE);

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

    public void paymentConfirm(View view)
    {

          final AlertDialog.Builder payment = new AlertDialog.Builder(PaymentMethod.this);
            payment.setTitle("Confirmation");
            payment.setMessage( "Class : " + cClass + "\n" +
                                "Timeslot : " + cTime + "\n" +
                                "Subtitle : " + cSub + "\n" +
                                "Price: " + cPri + "\n" +
                    "Click OK to confirm, or Cancel to return:");


            payment.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which) {
                    addPurchaseHistory(className,timeslot,subtitle,price, total);
                    Intent pay = new Intent(getApplicationContext(), SuccessPayment.class);
                    startActivity(pay);
                }

            });
            payment.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            payment.show();
    }

    private void addPurchaseHistory( String className, String timeslot, String subtitle, String price,  int total)
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String purchaseId = "P" + total;
        DatabaseReference myRef = database.getReference("Users").child(userID);
        myRef.child("Purchase").child(purchaseId).child("ClassName").setValue(className);
        myRef.child("Purchase").child(purchaseId).child("TimeSlot").setValue(timeslot);
        myRef.child("Purchase").child(purchaseId).child("Subtitle").setValue(subtitle);
        myRef.child("Purchase").child(purchaseId).child("Price").setValue(price);
        myRef.child("total").setValue(total);
    }

    public void homescreen(View view)
    {
        Intent home = new Intent(this, MainClassView.class);
        startActivity(home);
    }
}