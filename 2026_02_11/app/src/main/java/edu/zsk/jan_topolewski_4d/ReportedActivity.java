package edu.zsk.jan_topolewski_4d;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReportedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reported);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_reported), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView nameOutput = findViewById(R.id.reported_name);
        TextView surnameOutput = findViewById(R.id.reported_surname);
        TextView classOutput = findViewById(R.id.reported_class);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            nameOutput.setText(bundle.getString("Name"));
            surnameOutput.setText(bundle.getString("Surname"));
            classOutput.setText(bundle.getString("ClassName"));
        }

        Button button = findViewById(R.id.save_note_button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
}