package com.example.dream_cookery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainClassView extends AppCompatActivity {

    private ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class_view);

        menuIcon = findViewById(R.id.menuIcon);
        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MenuFragment();
            }
        });

    }

    public void WesternClass(View view) {
        Intent western = new Intent(this, WesternClass.class);
        startActivity(western);
    }

    public void MenuFragment()
    {
        MenuFragment menuFragment = new MenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.flFragment, menuFragment).addToBackStack(null).commit();
    }
}