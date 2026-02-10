package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        Bundle bundle = getArguments();
        assert bundle != null;

        String email = bundle.getString("email");
        String userName = bundle.getString("name");
        String surname = bundle.getString("surname");

        TextView emailOutput = view.findViewById(R.id.emailOutput);
        TextView nameOutput = view.findViewById(R.id.nameOutput);
        TextView surnameOutput = view.findViewById(R.id.surnameOutput);


        emailOutput.setText(String.format("%s %s", emailOutput.getText().toString(), email));
        nameOutput.setText(String.format("%s %s", nameOutput.getText().toString(), userName));
        surnameOutput.setText(String.format("%s %s", surnameOutput.getText().toString(), surname));

        return view;
    }
}
