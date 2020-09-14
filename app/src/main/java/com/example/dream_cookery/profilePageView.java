package com.example.dream_cookery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class profilePageView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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


}


