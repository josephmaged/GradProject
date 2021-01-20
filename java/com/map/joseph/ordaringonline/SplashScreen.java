package com.map.joseph.ordaringonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

   private ImageView logoimg;
   Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash_screen );

        getSupportActionBar().hide();


        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    logoimg= (ImageView) findViewById ( R.id.logoimg );
                    Animation animation= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.zoom_in);
                    logoimg.startAnimation(animation);
                    sleep(3000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent mainIntent=new Intent(SplashScreen.this,LogIn.class);
                    startActivity(mainIntent);
                }
            }
        };
        thread .start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
