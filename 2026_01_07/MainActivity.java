package com.example.a2026_01_07;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button);
        TextView resultView = findViewById(R.id.resultView);
        EditText email = findViewById(R.id.emailInput);
        EditText password1 = findViewById(R.id.passwordInput1);
        EditText password2 = findViewById(R.id.passwordInput2);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Pattern digitPattern = Pattern.compile("\\d");
                Pattern uppercaseLetterPattern = Pattern.compile("[A-Z]");
                Pattern lowercaseLetterPattern = Pattern.compile("[a-z]");

                String insertedEmail = email.getText().toString();
                String password1Text = password1.getText().toString();
                Matcher digitMatcher = digitPattern.matcher(password1Text);
                Matcher uppercaseLetterMatcher = uppercaseLetterPattern.matcher(password1Text);
                Matcher lowercaseLetterMatcher = lowercaseLetterPattern.matcher(password1Text);
                Matcher emailMatcher = Patterns.EMAIL_ADDRESS.matcher(insertedEmail);

                String message = "";

                if(!emailMatcher.matches()){
                    message = getString(R.string.nieprawidlowy_adres_e_mail);
                }
                else if(password1Text.length() < 8){
                    message = getString(R.string.informacja_o_wymogu_8_znakow);
                }
                else if(!password1Text.equals(password2.getText().toString())){
                    message = getString(R.string.hasla_sie_roznia);
                }
                else if(!digitMatcher.find()){
                    message = getString(R.string.informacja_o_wymogu_cyfr);
                }
                else if(!uppercaseLetterMatcher.find()){
                    message = getString(R.string.informacja_o_wymogu_duzej_litery);
                }
                else if(!lowercaseLetterMatcher.find()){
                    message = getString(R.string.informacja_o_wymogu_malej_litery);
                }
                else{
                    Intent i = new Intent(MainActivity.this, LoggedInActivity.class);
                    i.putExtra("Email", insertedEmail);
                    startActivity(i);
                }

                if(!message.isEmpty()){
                    resultView.setTextColor(Color.RED);
                    resultView.setText(message);
                }
            }
        });
    }


    @Override
    protected void onResume(){
        super.onResume();
        TextView resultView = findViewById(R.id.resultView);
        resultView.setTextColor(Color.BLACK);
        resultView.setText(R.string.autor);

        EditText email = findViewById(R.id.emailInput);
        EditText password1 = findViewById(R.id.passwordInput1);
        EditText password2 = findViewById(R.id.passwordInput2);
        email.setText("");
        password1.setText("");
        password2.setText("");
    }
}