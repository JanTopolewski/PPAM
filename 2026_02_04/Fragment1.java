package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;

public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        Button button = view.findViewById(R.id.button);
        EditText emailView = view.findViewById(R.id.emailInput);
        EditText userNameView = view.findViewById(R.id.nameInput);
        EditText surnameView = view.findViewById(R.id.surnameInput);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentActivity activity = getActivity();
                assert activity != null;

                String email = emailView.getText().toString();
                String userName = userNameView.getText().toString();
                String surname = surnameView.getText().toString();

                Matcher emailMatcher = Patterns.EMAIL_ADDRESS.matcher(email);

                if(email.isEmpty() || userName.isEmpty() || surname.isEmpty()){
                    Toast.makeText(activity, "Nie podano wszystkich danych!", Toast.LENGTH_SHORT).show();
                }
                else if(!emailMatcher.matches()){
                    Toast.makeText(activity, "Nieprawidłowy adres email!", Toast.LENGTH_SHORT).show();
                }
                else {
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment2 = new Fragment2();

                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);
                    bundle.putString("name", userName);
                    bundle.putString("surname", surname);
                    fragment2.setArguments(bundle);

                    fragmentTransaction.replace(R.id.main, fragment2);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }
}
