package com.cola.musicviewanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cola.musicviewanimation.view.AnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.start_btn);
        Button stop = findViewById(R.id.stop_btn);
        final AnimationView animationView = findViewById(R.id.animation_view);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.startAnimation();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.stopAnimation();
            }
        });
    }
}
