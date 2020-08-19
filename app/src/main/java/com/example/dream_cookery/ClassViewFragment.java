package com.example.dream_cookery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ClassViewFragment extends Fragment {

    TextView mainText;

    public ClassViewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_class_view, container, false);
        String mainTextString = getArguments().getString("mainText");

        //set text
        mainText = rootView.findViewById(R.id.class_type_main);

        mainText.setText(mainTextString);
        return rootView;
    }


}