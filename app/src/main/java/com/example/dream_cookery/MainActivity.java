package com.example.dream_cookery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rey.material.widget.CheckBox;


public class MainActivity extends AppCompatActivity {
    //firebase stuff
    private FirebaseAuth fBaseAuth;
    private FirebaseAuth.AuthStateListener fAuthStateListener;

    private CheckBox stayLogged;
    private Button loginButton;
    private TextView registerLink;
    private EditText inputEmail, inputPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        fBaseAuth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.email_input_layout);
        inputPassword = findViewById(R.id.password_input_layout);
        stayLogged = findViewById(R.id.remember_me_checkbox);
        loginButton =  findViewById(R.id.login_button);
        registerLink = findViewById(R.id.register_link);
        loadingBar = new ProgressDialog(this);


        StayLoggedIn();
        SetFAuthStateListener();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();

            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });
        //remember me
        stayLogged.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("stayLogged","true");
                    editor.apply();
                }
                else if (!compoundButton.isChecked())
                {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("stayLogged","false");
                    editor.apply();
                }
            }
        });

    }

    private void StayLoggedIn()
    {
        // stay logged in
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("stayLogged", "");
        if (checkbox.equals("false"))
        {
            // Logout
            FirebaseAuth.getInstance().signOut();
            // Set stay logged checkbox
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("stayLogged","true");
            editor.apply();
        }
    }

    private void SetFAuthStateListener()
    {
        fAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser fBaseUser = fBaseAuth.getCurrentUser();
                if (fBaseUser != null)
                {
                    Toast.makeText(MainActivity.this, "You are logged in...", Toast.LENGTH_SHORT);
                    //remove to remember me
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("stayLogged","false");
                    editor.apply();
                    openMainClassView();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please login again...", Toast.LENGTH_SHORT);
                }

            }
        };
    }

    private void LoginUser()
    {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else if (password.length() < 6)
        {
            Toast.makeText(this, "Password has to be 6 characters or longer .", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessAccount(email, password);
        }

    }

    private void AllowAccessAccount(final String email, final String password) {
        fBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    loadingBar.dismiss();
                    openMainClassView();

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login Failed!!! ", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });

    }


    public void openMainClassView() {
        Intent intent = new Intent(this, MainClassView.class);
        startActivity(intent);
    }

    public void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //set the state listener
        fBaseAuth.addAuthStateListener(fAuthStateListener);
    }

    @Override
    public void onBackPressed() {
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exit);
        finish();
        System.exit(0);
    }
}