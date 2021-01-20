package com.map.joseph.ordaringonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;

public class EmptyActivity extends AppCompatActivity {

    ImageView imgretry;
    TextView txtretry;
    FrameLayout frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_empty );

        getSupportActionBar().hide();

        imgretry =findViewById ( R.id.imgretry );
        imgretry.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                OrdaringDB db = new OrdaringDB ();
                Connection con;
                con=db.connectionclass ();
                if(con==null)
                {
                    Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
                }
                else
                {
                    Intent i = new Intent (EmptyActivity.this, MainMenu.class );
                    startActivity ( i );
                }
            }
        } );


        txtretry =findViewById ( R.id.txtretry );
        txtretry.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                OrdaringDB db = new OrdaringDB ();
                Connection con;
                con=db.connectionclass ();
                if(con==null)
                {
                    Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
                }
                else
                {
                    Intent i = new Intent (EmptyActivity.this, MainMenu.class );
                    startActivity ( i );
                }
            }
        } );

        frame = findViewById ( R.id.frame );
        frame.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                OrdaringDB db = new OrdaringDB ();
                Connection con;
                con=db.connectionclass ();
                if(con==null)
                {
                    Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
                }
                else
                {
                    Intent i = new Intent (EmptyActivity.this, MainMenu.class );
                    startActivity ( i );
                }
            }
        } );
    }


    //finish activity on device back buttom
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}
