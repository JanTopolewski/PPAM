package edu.zsk.egzamin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String[] SPECIES = {
          "Pies",
          "Kot",
          "Świnka morska"
    };

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

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPECIES);
        final ListView listView = findViewById(R.id.speciesList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        SeekBar seekBar = findViewById(R.id.ageInput);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Object listItem = parent.getItemAtPosition(position);

            if (listItem != null){
                if (listItem == "Pies"){
                    seekBar.setMax(18);
                }
                else if (listItem == "Kot"){
                    seekBar.setMax(20);
                }
                else if (listItem == "Świnka morska"){
                    seekBar.setMax(9);
                }
            }
        });

        TextView ageOutput = findViewById(R.id.ageInfo);
        EditText nameInput = findViewById(R.id.nameInput);
        EditText objectiveInput = findViewById(R.id.objectiveInput);
        EditText timeInput = findViewById(R.id.timeInput);
        TextView resultOutput = findViewById(R.id.resultOutput);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser){
                progress = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
                String result = "Ile ma lat? " + progress;
                ageOutput.setText(result);
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String result = nameInput.getText().toString() + ", " + listView.getItemAtPosition(listView.getCheckedItemPosition())
                        + ", " + seekBar.getProgress() + ", " + objectiveInput.getText().toString() + ", " + timeInput.getText().toString();

                resultOutput.setText(result);

                NotificationChannel channel = new NotificationChannel("visit_to_the_vet", "Wizyta u weterynarza", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(MainActivity.this, "visit_to_the_vet")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Informacja o wizycie")
                        .setContentText(result)
                        .build();

                int id = 1;
                notificationManager.notify(id, notification);
            }
        });
    }
}