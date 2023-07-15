package com.example.gestionempresarial.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.gestionempresarial.R;

public class LTSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ltsplash);

        ImageView imageView = findViewById(R.id.img_icon);
        // Animación: Trasladar Elemento de Arriba hacia Abajo
        TranslateAnimation an = new TranslateAnimation(0.0f,  0.0f,  -1600.0f,  0.0f);
        an.setDuration(1000);
        imageView.startAnimation(an);

        /****** Create Thread that will sleep for 5 seconds****/
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(3000);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),LoginActivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        background.start();
    }
}