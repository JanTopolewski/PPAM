package edu.zsk.a2026_09_07;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int gameResult = 0;

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
        TextView box1 = findViewById(R.id.textInfoBox1);
        TextView box2 = findViewById(R.id.textInfoBox2);
        List<ImageView> diceImages = List.of(
            findViewById(R.id.imageView),
            findViewById(R.id.imageView2),
            findViewById(R.id.imageView3),
            findViewById(R.id.imageView4),
            findViewById(R.id.imageView5)
        );
        int[] diceDrawables = {
                R.drawable.k1,
                R.drawable.k2,
                R.drawable.k3,
                R.drawable.k4,
                R.drawable.k5,
                R.drawable.k6
        };

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                List<Integer> randomResults = new ArrayList<>();
                int throwResult = 0;

                for(int i=0; i<5; i++){
                    int dice = (int)(Math.random() * 6 + 1);

                    if(!randomResults.contains(dice)){
                        randomResults.add(dice);
                    }
                    else{
                        throwResult += dice;
                        gameResult += dice;
                    }

                    diceImages.get(i).setImageResource(diceDrawables[dice - 1]);
                }

                box1.setText(String.format(Locale.getDefault(),"Wynik tego losowania: %d", throwResult));
                box2.setText(String.format(Locale.getDefault(),"Wynik gry: %d", gameResult));
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                for(ImageView image : diceImages){
                    image.setImageResource(R.drawable.question);
                }
                gameResult = 0;
                box1.setText(R.string.wynik_tego_losowania_0);
                box2.setText(String.format(Locale.getDefault(),"Wynik gry: %d", gameResult));
            }
        });
    }
}