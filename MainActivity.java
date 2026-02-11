package edu.zsk.jan_topolewski_4d;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        Button button = findViewById(R.id.save_note_button);
        EditText nameInput = findViewById(R.id.input_name);
        EditText surnameInput = findViewById(R.id.surname_input);
        EditText classInput = findViewById(R.id.class_input);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String dataName = nameInput.getText().toString();
                String dataSurname = surnameInput.getText().toString();
                String dataClass = classInput.getText().toString();

                if(dataName.isEmpty() || dataSurname.isEmpty() || dataClass.isEmpty()){
                    Toast.makeText(R.id.main, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}