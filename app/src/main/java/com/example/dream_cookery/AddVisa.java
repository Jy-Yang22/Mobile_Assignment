package com.example.dream_cookery;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CvvEditText;
import com.braintreepayments.cardform.view.ExpirationDateEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddVisa extends AppCompatActivity {

    EditText cardname;
    CardEditText cardnum;
    CvvEditText cvv;
    ExpirationDateEditText cardexp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visa);
        cardnum = findViewById(R.id.cc_number_box);
        cardname = findViewById(R.id.cc_name_box);
        cvv = findViewById(R.id.cvv_box);
        cardexp = findViewById(R.id.expired_date_box);

    }

    public void onClickShowAlert(View view) {
        final String ccNum = String.valueOf(cardnum.getText());
        final String ccExp = cardexp.getText().toString();
        final String ccCvv = cvv.getText().toString();
        final String ccName = cardname.getText().toString();


        if (ccName == "EMPTY")
            Toast.makeText(AddVisa.this, "Please fill up your credit card name.", Toast.LENGTH_SHORT).show();
        else if (ccNum.isEmpty() || cardnum.length() != 16 )
            Toast.makeText(AddVisa.this, "Please input the correct credit card number.", Toast.LENGTH_SHORT).show();
        else if (ccExp.isEmpty() || ccExp.length() != 4 )
            Toast.makeText(AddVisa.this, "Please input the correct credit card expire date.", Toast.LENGTH_SHORT).show();
        else if (ccCvv.isEmpty() || ccCvv.length() != 3 )
            Toast.makeText(AddVisa.this, "Please input the correct credit card cvv.", Toast.LENGTH_SHORT).show();
        else {

            final AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(AddVisa.this);
            myAlertBuilder.setTitle("Confirmation");
            myAlertBuilder.setMessage("Card Name : " + ccName + "\n" +
                    "Card No. : " + ccNum + "\n" +
                    "Card Expire Date: " + ccExp + "\n" +
                    "Cvv : " + ccCvv + "\n\n" +
                    "Click OK to confirm, or Cancel to return:");

            myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                            public void onClick(DialogInterface dialog, int which) {
                                addCreditCard (ccName, ccNum, ccExp, ccCvv);
                            Intent successAddedCard = new Intent(getApplicationContext(), SuccessAddedCard.class);
                            startActivity(successAddedCard);
                        }

                    });
            myAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                        public void onClick(DialogInterface dialog, int which) {
                            // User cancelled the dialog.

                        }
                    });
            myAlertBuilder.show();
        }
    }

    private void addCreditCard ( final String ccName, final String ccNum, final String ccExp, final String ccCvv) {
        final FirebaseAuth fBaseAuth = FirebaseAuth.getInstance();
        String userID = fBaseAuth.getCurrentUser().getUid();
        char first = ccNum.charAt(0);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(userID).child("CC_Info");
        if (first == '2' || first == '5') {
            myRef.child("mastercard").child("ccname").setValue(ccName);
            myRef.child("mastercard").child("ccNum").setValue(ccNum);
            myRef.child("mastercard").child("ExpDate").setValue(ccExp);
            myRef.child("mastercard").child("cvv").setValue(ccCvv);
        } else {
            myRef.child("visa").child("ccname").setValue(ccName);
            myRef.child("visa").child("ccNum").setValue(ccNum);
            myRef.child("visa").child("ExpDate").setValue(ccExp);
            myRef.child("visa").child("cvv").setValue(ccCvv);
        }
    }

    public void mainMenu(View view)
    {
        Intent menu = new Intent(this, MainClassView.class);
        startActivity(menu);
    }

}