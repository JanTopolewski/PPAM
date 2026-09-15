package edu.zsk.a2026_09_14;

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

        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        EditText washCycleNumberInput = findViewById(R.id.washCycleNumberInput);
        TextView washingMachineResultBox = findViewById(R.id.washingMachineResultBox);
        TextView vacuumCleanerOnBox = findViewById(R.id.vacuumCleanerOnBox);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int washCycleNumber = 0;
                String input = washCycleNumberInput.getText().toString();

                if (!input.isEmpty()){
                    washCycleNumber = Integer.parseInt(input);
                }

                if (washCycleNumber >= 1 && washCycleNumber <=12) {
                    washingMachineResultBox.setText(String.format("%s%s", getString(R.string.numer_prania), washCycleNumber));
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button2.getText().toString().equals(getString(R.string.wlacz))){
                    button2.setText(R.string.wylacz);
                    vacuumCleanerOnBox.setText(R.string.odkurzacz_wlaczony);
                }
                else {
                    button2.setText(R.string.wlacz);
                    vacuumCleanerOnBox.setText(R.string.odkurzacz_wylaczony);
                }
            }
        });
    }
}