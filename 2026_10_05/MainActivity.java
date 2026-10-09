package edu.zsk.a2026_10_05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int likesNumber = 0;

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

        Button likeButton = findViewById(R.id.likeButton);
        Button deleteLikeButton = findViewById(R.id.deleteLikeButton);
        TextView likesNumberInfo = findViewById(R.id.likesNumberInfo);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likesNumber++;
                likesNumberInfo.setText(String.format(Locale.getDefault(), "%d%s", likesNumber, getString(R.string.polubien)));
            }
        });

        deleteLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likesNumber > 0){
                    likesNumber--;
                    likesNumberInfo.setText(String.format(Locale.getDefault(), "%d%s", likesNumber, getString(R.string.polubien)));
                }
            }
        });
    }
}