package edu.zsk.a2026_10_26;

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
    String secretText;

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

        Button encryptButton = findViewById(R.id.encryptButton);
        Button saveToFileButton = findViewById(R.id.saveToFileButton);
        EditText encryptionKey = findViewById(R.id.encryptionKeyInput);
        EditText textForEncryptionInput = findViewById(R.id.textForEncryptionInput);
        TextView resultBox = findViewById(R.id.encryptionResultBox);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder secretTextBuilder = new StringBuilder();
                String publicText = textForEncryptionInput.getText().toString();
                int key = Integer.parseInt(encryptionKey.getText().toString());
                for(int i=0; i<publicText.length(); i++){
                    if(publicText.charAt(i) != ' '){
                        int shift = key % 26;
                        if(publicText.charAt(i) + shift < 97){
                            shift += 26;
                        }
                        else if(publicText.charAt(i) + shift > 122){
                            shift -= 26;
                        }
                        secretTextBuilder.append((char) (publicText.charAt(i) + shift));
                    }
                    else{
                        secretTextBuilder.append(publicText.charAt(i));
                    }
                }
                secretText = secretTextBuilder.toString();
                resultBox.setText(secretText);
            }
        });

        saveToFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}