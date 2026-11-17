package edu.zsk.a2026_11_02;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int redColorValue = 255;
    private int greenColorValue = 255;
    private int blueColorValue = 255;

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

        SeekBar redColorSeekBar = findViewById(R.id.redColorInput);
        SeekBar greenColorSeekBar = findViewById(R.id.greenColorInput);
        SeekBar blueColorSeekBar = findViewById(R.id.blueColorInput);
        TextView redColorOutput = findViewById(R.id.redColorValueOutput);
        TextView greenColorOutput = findViewById(R.id.greenColorValueOutput);
        TextView blueColorOutput = findViewById(R.id.blueColorValueOutput);
        TextView resultBox = findViewById(R.id.resultBox);
        Button button  = findViewById(R.id.button);

        redColorSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redColorValue = progress;
                redColorOutput.setText(String.valueOf(redColorValue));
                updateRectangleColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        greenColorSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenColorValue = progress;
                greenColorOutput.setText(String.valueOf(greenColorValue));
                updateRectangleColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        blueColorSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueColorValue = progress;
                blueColorOutput.setText(String.valueOf(blueColorValue));
                updateRectangleColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultBox.setText(String.format(Locale.getDefault(), "%d, %d, %d", redColorValue, greenColorValue, blueColorValue));
                resultBox.setBackgroundColor(Color.rgb(redColorValue, greenColorValue, blueColorValue));
            }
        });
    }

    private void updateRectangleColor(){
        View colorfulRectangle = findViewById(R.id.colorfulRectangle);
        GradientDrawable background = (GradientDrawable) colorfulRectangle.getBackground();
        background.setColor(Color.rgb(redColorValue, greenColorValue, blueColorValue));
    }
}