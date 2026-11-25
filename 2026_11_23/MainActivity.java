package edu.zsk.a2026_11_23;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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

        Button checkPriceButton = findViewById(R.id.checkPriceButton);
        Button confirmButton = findViewById(R.id.confirmButton);
        ImageView packageTypeImage = findViewById(R.id.packageTypeImage);
        TextView packageTypePriceBox = findViewById(R.id.packageTypePriceBox);
        RadioButton postcardButton = findViewById(R.id.postcardButton);
        RadioButton letterButton = findViewById(R.id.letterButton);
        EditText postalCodeInput = findViewById(R.id.postalCodeInput);

        checkPriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postcardButton.isChecked()){
                    packageTypeImage.setImageResource(R.drawable.pocztowka);
                    packageTypePriceBox.setText(R.string.cena_1_zl);
                }
                else if(letterButton.isChecked()){
                    packageTypeImage.setImageResource(R.drawable.list);
                    packageTypePriceBox.setText(R.string.cena_1_5_zl);
                }
                else{
                    packageTypeImage.setImageResource(R.drawable.paczka);
                    packageTypePriceBox.setText(R.string.cena_10_zl);
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postalCodeInput.getText().length() == 5){
                    boolean correct = true;
                    for(int i=0; i<5; i++){
                        if(!postalCodeInput.getText().toString().matches("\\d{5}")){
                            correct = false;
                        }
                    }

                    if(correct){
                        Toast.makeText(MainActivity.this, "Dane przesyłki zostały wprowadzone", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Kod pocztowy powinien się składać z samych cyfr", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Nieprawidłowa liczba cyfr w kodzie pocztowym", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}