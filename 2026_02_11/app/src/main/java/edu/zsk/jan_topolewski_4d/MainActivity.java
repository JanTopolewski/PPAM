package edu.zsk.jan_topolewski_4d;

import android.app.ProgressDialog;
import android.content.Intent;
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
                if(nameInput.getText().toString().isEmpty() || surnameInput.getText().toString().isEmpty() || classInput.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.wypelnij_wszystkie_pola, Toast.LENGTH_SHORT).show();
                }
                else{
                    String dataName = nameInput.getText().toString();
                    String dataSurname = surnameInput.getText().toString();
                    String dataClass = classInput.getText().toString();

                    Intent i = new Intent(MainActivity.this, ReportedActivity.class);
                    i.putExtra("Name", dataName);
                    i.putExtra("Surname", dataSurname);
                    i.putExtra("ClassName", dataClass);

                    final ProgressDialog progress = new ProgressDialog(MainActivity.this);
                    progress.setTitle(getString(R.string.dodaje_uwage));
                    progress.setMessage(getString(R.string.prosze_czekac));
                    progress.show();

                    new Thread(new Runnable() {
                        public void run() {
                            nameInput.setText("");
                            surnameInput.setText("");
                            classInput.setText("");

                            try {
                                Thread.sleep(2000);
                            } catch(InterruptedException e){
                                e.printStackTrace();
                            }

                            progress.dismiss();

                            startActivity(i);
                        }
                    }).start();
                }
            }
        });
    }
}