package com.example.dream_cookery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.dream_cookery.Models.Classes;
import com.example.dream_cookery.ViewHolder.ClassViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class ClassViewFragment extends Fragment {

    TextView mainText;
    DatabaseReference ClassesRef;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    public ClassViewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_class_view, container, false);
        String mainTextString = getArguments().getString("mainText");

        ClassesRef = FirebaseDatabase.getInstance().getReference().child("Classes");
        //set text
        mainText = rootView.findViewById(R.id.class_type_main);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));


        mainText.setText(mainTextString);
        return rootView;
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