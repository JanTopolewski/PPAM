package edu.zsk.a2026_11_16;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    List<String> positions = new ArrayList<>(Arrays.asList("", "Kierownik", "Starszy programista", "Młodszy programista", "Tester"));
    String password;

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

        EditText nameInput = findViewById(R.id.nameInput);
        EditText surnameInput = findViewById(R.id.surnameInput);
        EditText passwordLengthInput = findViewById(R.id.passwordLengthInput);
        Spinner positionInput = findViewById(R.id.positionInput);
        Button generatePasswordButton = findViewById(R.id.generatePasswordButton);
        Button confirmButton = findViewById(R.id.confirmButton);
        CheckBox passwordLettersSizeCheckbox = findViewById(R.id.passwordLettersSizeCheckbox);
        CheckBox passwordDigitsCheckbox = findViewById(R.id.passwordDigitsCheckbox);
        CheckBox passwordSpecialCharsCheckbox = findViewById(R.id.passwordSpecialCharsCheckbox);


        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positions);
        positionInput.setAdapter(listAdapter);

        generatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();

                String smallLetters = "abcdefghijklmnopqrstuvwxyz";
                String bigLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                String digits = "0123456789";
                String specialChars = "!@#$%^&*()_+-=";
                StringBuilder temporaryPassword = new StringBuilder();

                int charsNumber = Integer.parseInt(passwordLengthInput.getText().toString());

                if(passwordLettersSizeCheckbox.isChecked() && temporaryPassword.length() < charsNumber){
                    temporaryPassword.append(bigLetters.charAt(random.nextInt(bigLetters.length())));
                }

                if(passwordDigitsCheckbox.isChecked() && temporaryPassword.length() < charsNumber){
                    temporaryPassword.append(digits.charAt(random.nextInt(digits.length())));
                }

                if(passwordSpecialCharsCheckbox.isChecked() && temporaryPassword.length() < charsNumber){
                    temporaryPassword.append(specialChars.charAt(random.nextInt(specialChars.length())));
                }

                while(temporaryPassword.length() < charsNumber){
                    temporaryPassword.append(smallLetters.charAt(random.nextInt(smallLetters.length())));
                }

                password = temporaryPassword.toString();

                Toast.makeText(MainActivity.this, password, Toast.LENGTH_LONG).show();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        MainActivity.this,
                        String.format(Locale.getDefault(), "Dane pracownika: %s %s %s Hasło: %s", nameInput.getText().toString(), surnameInput.getText().toString(), positionInput.getSelectedItem().toString(), password),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
