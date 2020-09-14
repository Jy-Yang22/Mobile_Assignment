package com.example.dream_cookery;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class editProfileView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] gender = {"Male", "Female"};

    private static final String TAG = editProfileView.class.getSimpleName();

    Button Save;
    EditText editName, editEmail, editPhone;
    String eName;
    String eEmail;
    int ePhone;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

        @Override
        protected void onCreate (Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.edit_profile);

            Save = (Button)findViewById(R.id.save_Button);
            editName = (EditText)findViewById(R.id.profile_name);
            editEmail = (EditText)findViewById(R.id.profile_email);
            editPhone = (EditText)findViewById(R.id.profile_phone);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

            String e_Name = prefs.getString("eName","");
            editName.setText(e_Name);
            String e_Email = prefs.getString("eEmail","");
            editEmail.setText(e_Email);
            int e_Phone = prefs.getInt("ePhone", 0);
            editPhone.setText(""+e_Phone);

            Save.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    eName = editName.getText().toString();
                    eEmail = editEmail.getText().toString();
                    ePhone = Integer.parseInt(editPhone.getText().toString());
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(editProfileView.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("eName",eName);
                    editor.putString("eEmail",eEmail);
                    editor.putInt("ePhone", ePhone);
                    editor.apply();
                }
            });
            Spinner spin = (Spinner) findViewById(R.id.gender);
            spin.setOnItemSelectedListener(this);

            ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, gender);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spin.setAdapter(aa);

            mDisplayDate = (TextView)findViewById(R.id.profile_birth);
            mDisplayDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            editProfileView.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateSetListener,
                            year,month,day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                    String date = month + "/" + day + "/" + year;
                    mDisplayDate.setText(date);
                }
            };
        }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
    {
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {
    }

    /*public void BackToProfile(View arg0) {
        Intent it1 = new Intent(getApplicationContext(), profilePageView.class);
        startActivity(it1);
        Toast.makeText(getApplicationContext(),"Edit successful",Toast.LENGTH_SHORT).show();
    }*/

}