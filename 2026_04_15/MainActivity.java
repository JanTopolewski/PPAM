package com.example.a2026_04_15;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int clicksNumber = 0;
    private final String KEY1 = "CLICKS";
    private String message = "";
    private final String KEY2 = "MESSAGE";

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
        EditText inputName = findViewById(R.id.editName);
        EditText inputEmail = findViewById(R.id.editEmail);
        TextView txt1 = findViewById(R.id.txt1);
        TextView txt2 = findViewById(R.id.txt2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();

                if (!name.isEmpty() && !email.isEmpty()){
                    message = String.format("Witaj, %s! Twój adres e-mail to: %s", name, email);
                    txt1.setText(message);

                    ++clicksNumber;
                    txt2.setText(String.format("Kliknąłeś przycisk %s razy", clicksNumber));
                }
                else {
                    message = "";
                    txt1.setText(message);
                    Toast.makeText(MainActivity.this, R.string.najpierw_uzupelnij_swoje_dane, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY1, clicksNumber);
        outState.putString(KEY2, message);
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        message = savedInstanceState.getString(KEY2);
        TextView txt1 = findViewById(R.id.txt1);
        txt1.setText(message);

        clicksNumber = savedInstanceState.getInt(KEY1);
        TextView txt2 = findViewById(R.id.txt2);
        txt2.setText(String.format("Kliknąłeś przycisk %s razy", clicksNumber));
    }
}