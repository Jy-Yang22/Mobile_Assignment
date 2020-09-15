package com.example.dream_cookery;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MenuFragment extends Fragment {

    private TextView homeText, profileText, classSchedule;


    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        classSchedule = rootView.findViewById(R.id.class_schedule);
        classSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), purchaseHistoryView.class);
                startActivity(intent);
            }
        });

        homeText = rootView.findViewById(R.id.home);
        homeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });

        profileText = rootView.findViewById(R.id.profile);
        profileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile();
            }
        });


        return rootView;
    }



    private void goToHome()
    {
        Intent intent = new Intent(getActivity(), MainClassView.class);
        startActivity(intent);
    }

    private void goToProfile()
    {
        Intent intent = new Intent(getActivity(), profilePageView.class);
        startActivity(intent);
    }


}