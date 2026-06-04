package edu.zsk.a2026_04_29;

import android.os.Bundle;
import android.view.View;

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
    }

    public void onImageClick(View v){
        int imageToSend;
        int viewId = v.getId();

        if (viewId == R.id.imageView) {
            imageToSend = R.drawable.gory;
        } else if (viewId == R.id.imageView2) {
            imageToSend = R.drawable.niedzwiedz;
        } else if (viewId == R.id.imageView3) {
            imageToSend = R.drawable.lodowce;
        } else if (viewId == R.id.imageView4) {
            imageToSend = R.drawable.zebry;
        } else if (viewId == R.id.imageView5) {
            imageToSend = R.drawable.slon;
        } else if (viewId == R.id.imageView6) {
            imageToSend = R.drawable.wiezowce;
        } else if (viewId == R.id.imageView7) {
            imageToSend = R.drawable.slimak;
        } else if (viewId == R.id.imageView8) {
            imageToSend = R.drawable.ptak;
        } else if (viewId == R.id.imageView9) {
            imageToSend = R.drawable.pszczola;
        } else if (viewId == R.id.imageView10) {
            imageToSend = R.drawable.pole;
        } else if (viewId == R.id.imageView11) {
            imageToSend = R.drawable.motyl;
        } else if (viewId == R.id.imageView12) {
            imageToSend = R.drawable.kwiatek;
        } else if (viewId == R.id.imageView13) {
            imageToSend = R.drawable.kot;
        } else if (viewId == R.id.imageView14) {
            imageToSend = R.drawable.jelen;
        } else if (viewId == R.id.imageView15) {
            imageToSend = R.drawable.kosciol;
        } else {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("image", imageToSend);

        Fragment1 dialog = new Fragment1();
        dialog.setArguments(bundle);

        dialog.show(getSupportFragmentManager(), "FragmentXdDialog");
    }
}