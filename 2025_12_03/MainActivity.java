package com.example.a2025_12_03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
                String insertedEmail = email.getText().toString();
                if(!insertedEmail.contains("@")){
                    resultView.setText(R.string.nieprawidlowy_adres_e_mail);
                }
                else if(!password1.getText().toString().equals(password2.getText().toString())){
                    resultView.setText(R.string.hasla_sie_roznia);
                }
                else{
                    String resultMessage = "Witaj " + insertedEmail;
                    resultView.setText(resultMessage);
                }
            }
        });
    }
}