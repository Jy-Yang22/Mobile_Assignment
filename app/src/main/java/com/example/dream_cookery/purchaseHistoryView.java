package com.example.dream_cookery;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dream_cookery.Models.Classes;
import com.example.dream_cookery.Models.History;
import com.example.dream_cookery.ViewHolders.ClassViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class purchaseHistoryView extends AppCompatActivity {


    DatabaseReference HistoryRef;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    String currentID;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_history);

        ImageButton backPress = findViewById(R.id.backPurchaseHistory);
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(purchaseHistoryView.this, profilePageView.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.PurchaseHistory);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        firebaseAuth = FirebaseAuth.getInstance();
        currentID = firebaseAuth.getCurrentUser().getUid();

        HistoryRef = FirebaseDatabase.getInstance().getReference("Users").child(currentID).child("Purchase");

    }

    @Override
    protected void onStart() {
        super.onStart();



        FirebaseRecyclerOptions<History> options =
                new FirebaseRecyclerOptions.Builder<History>()
                        .setQuery(HistoryRef, History.class)
                        .build();
        Log.d("hello", "no");
        FirebaseRecyclerAdapter<History, ClassViewHolder> adapter =
                new FirebaseRecyclerAdapter<History, ClassViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ClassViewHolder classViewHolder, int i, @NonNull final History history)
                    {
                        classViewHolder.txtClassName.setText(history.getClassName());
                        classViewHolder.txtClassDescription.setText(history.getSubtitle() + " " + history.getTimeSlot());
                        classViewHolder.txtClassPrice.setText(history.getPrice());


                        /*classViewHolder.txtClassName.setText(history.getcName());
                        classViewHolder.txtClassDescription.setText(history.getcDescription());
                        classViewHolder.txtClassPrice.setText("Price = RM " + history.getcPrice() );
                        Picasso.get().load(history.getcImage()).into(classViewHolder.imageView);*/

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

