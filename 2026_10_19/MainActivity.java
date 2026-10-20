package edu.zsk.a2026_10_19;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String[] species = {
        "Pies",
        "Kot",
        "Świnka morska"
    };

    private int speciesIndex;

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

        ListView speciesList = findViewById(R.id.speciesList);
        SeekBar ageInput = findViewById(R.id.ageInput);
        TextView ageLabel = findViewById(R.id.ageLabel);
        TextView resultBox = findViewById(R.id.resultBox);
        Button button = findViewById(R.id.button);
        EditText timeInput = findViewById(R.id.timeInput);
        EditText nameAndSurnameInput = findViewById(R.id.nameAndSurnameInput);
        EditText purposeInput = findViewById(R.id.purposeInput);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, species);
        speciesList.setAdapter(adapter);

        speciesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView temporaryTextView = (TextView) view;
                speciesIndex = position;
                String species = temporaryTextView.getText().toString();

                if(species.equals("Pies")){
                    ageInput.setMax(18);
                }
                else if(species.equals("Kot")){
                    ageInput.setMax(20);
                }
                else{
                    ageInput.setMax(9);
                }
            }
        });

        ageInput.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageLabel.setText(String.format(Locale.getDefault(), "Ile ma lat? %d", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultBox.setText(String.format(
                    Locale.getDefault(),
                    "%s,%s,%d,%s,%s",
                    nameAndSurnameInput.getText().toString(),
                    species[speciesIndex],
                    ageInput.getProgress(),
                    purposeInput.getText().toString(),
                    timeInput.getText().toString()
                ));
            }
        });
    }
}