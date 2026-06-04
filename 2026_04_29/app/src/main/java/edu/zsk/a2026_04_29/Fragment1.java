package edu.zsk.a2026_04_29;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

public class Fragment1 extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        Dialog dialog = getDialog();
        assert dialog != null;
        dialog.setTitle("Wybrane zdjęcie");
        setCancelable(false);

        ImageView img = view.findViewById(R.id.imageViewDialog);
        Bundle args = getArguments();

        if(args != null){
            int image = args.getInt("image");
            img.setImageResource(image);

            img.requestLayout();
        }

        Button close = view.findViewById(R.id.buttonClose);
        close.setOnClickListener(v -> dismiss());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getDialog() != null && getDialog().getWindow() != null) {

            getDialog().getWindow().setLayout(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT
                                        );
        }
    }
}
