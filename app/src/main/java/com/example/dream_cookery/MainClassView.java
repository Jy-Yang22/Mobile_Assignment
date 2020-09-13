package com.example.dream_cookery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dream_cookery.Models.Classes;
import com.example.dream_cookery.ViewHolder.ClassViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainClassView extends AppCompatActivity {

    private ImageView menuIcon;
    private TextView westernText;


    DatabaseReference ClassesRef;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    //Menu Fragment
    private boolean isMenuFragmentDisplayed = false;
    private boolean isClassViewFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class_view);

        westernText = findViewById(R.id.western_text);
        menuIcon = findViewById(R.id.menuIcon);

        ClassesRef = FirebaseDatabase.getInstance().getReference().child("Classes");


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        westernText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!isClassViewFragmentDisplayed && !isMenuFragmentDisplayed)
                {
                    openClassViewFragment();
                }

            }
        });


        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!isMenuFragmentDisplayed) {
                    openMenuFragment();

                }

                else
                    closeMenuFragment();
            }
        });

        if(savedInstanceState != null)
        {
            isMenuFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isMenuFragmentDisplayed = false;
        isClassViewFragmentDisplayed = false;
    }

    public void openClassViewFragment() {
        /*Intent western = new Intent(this, WesternClass.class);
        startActivity(western);*/
        ClassViewFragment classViewFragment = new ClassViewFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.flFragment, classViewFragment).addToBackStack(null).commit();
        Bundle bundle = new Bundle();
        bundle.putString("mainText", "Western");

        classViewFragment.setArguments(bundle);
        isClassViewFragmentDisplayed = true;
    }

    public void openMenuFragment()
    {
        MenuFragment menuFragment = new MenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.flFragment, menuFragment).addToBackStack(null).commit();
        isMenuFragmentDisplayed = true;
    }

    public void closeMenuFragment()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MenuFragment menuFragment = (MenuFragment) fragmentManager.findFragmentById(R.id.flFragment);
        if(menuFragment != null)
        {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(menuFragment).commit();
        }
        isMenuFragmentDisplayed = false;

    }

    public void onSavedInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putBoolean(STATE_FRAGMENT, isMenuFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Classes> options =
                new FirebaseRecyclerOptions.Builder<Classes>().setQuery(ClassesRef, Classes.class).build();

        FirebaseRecyclerAdapter<Classes, ClassViewHolder> adapter =
                new FirebaseRecyclerAdapter<Classes, ClassViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ClassViewHolder classViewHolder, int i, @NonNull Classes classes)
                    {
                        classViewHolder.txtClassName.setText(classes.getcName());
                        classViewHolder.txtClassDescription.setText(classes.getcDescription());
                        classViewHolder.txtClassPrice.setText("Price = RM " + String.format(".2f", classes.getcPrice()) );
                        Picasso.get().load(classes.getcImage()).into(classViewHolder.imageView);
                    }

                    @NonNull
                    @Override
                    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_layout, parent, false);
                        ClassViewHolder holder = new ClassViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


}