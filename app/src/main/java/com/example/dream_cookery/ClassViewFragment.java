package com.example.dream_cookery;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dream_cookery.Models.Classes;
import com.example.dream_cookery.ViewHolders.ClassViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class ClassViewFragment extends Fragment {

    TextView mainText;
    DatabaseReference ClassesRef;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    public static String mainTextString;
    public ClassViewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_class_view, container, false);


        //set text
        mainText = rootView.findViewById(R.id.class_type_main);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);



        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(getArguments().getString("mainText")== "Western")
        {
            mainTextString = getArguments().getString("mainText");
            ClassesRef = FirebaseDatabase.getInstance().getReference("Classes").child(mainTextString);
            Log.d("Hello", "Western");
            mainText.setText(mainTextString);
        }
        else if(getArguments().get("mainText") == "Malaysian")
        {
            mainTextString = getArguments().getString("mainText");
            ClassesRef = FirebaseDatabase.getInstance().getReference("Classes").child(mainTextString);
            Log.d("Hello", "Malaysian");
            mainText.setText(mainTextString);
        }
        else if(getArguments().get("mainText") == "Chinese")
        {
            mainTextString = getArguments().getString("mainText");
            ClassesRef = FirebaseDatabase.getInstance().getReference("Classes").child(mainTextString);
            Log.d("Hello", "Chinese");
            mainText.setText(mainTextString);
        }
        else if(getArguments().get("mainText") == "Dessert")
        {
            mainTextString = getArguments().getString("mainText");
            ClassesRef = FirebaseDatabase.getInstance().getReference("Classes").child(mainTextString);
            Log.d("Hello", "Dessert");
            mainText.setText(mainTextString);
        }

        FirebaseRecyclerOptions<Classes> options =
                new FirebaseRecyclerOptions.Builder<Classes>()
                        .setQuery(ClassesRef, Classes.class)
                        .build();

        FirebaseRecyclerAdapter<Classes, ClassViewHolder> adapter =
                new FirebaseRecyclerAdapter<Classes, ClassViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ClassViewHolder classViewHolder, int i, @NonNull final Classes classes)
                    {
                        classViewHolder.txtClassName.setText(classes.getcName());
                        classViewHolder.txtClassDescription.setText(classes.getcDescription());
                        classViewHolder.txtClassPrice.setText("Price = RM " + classes.getcPrice() );
                        Picasso.get().load(classes.getcImage()).into(classViewHolder.imageView);

                        classViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity().getApplicationContext(), ClassInfoOverview.class);
                                intent.putExtra("cID", classes.getcID());
                                intent.putExtra("Category", mainTextString);
                                startActivity(intent);
                            }
                        });
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