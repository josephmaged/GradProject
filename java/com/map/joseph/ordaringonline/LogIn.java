package com.map.joseph.ordaringonline;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogIn extends AppCompatActivity {

    EditText txtuser,txtpass;
    Button btnlogin;
    SharedPreferences sp;
    TextView txttyporeg2;
    public static String user,email,usname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_log_in );


        txtuser = (EditText) findViewById ( R.id.txtloguser );
        txtpass = (EditText) findViewById ( R.id.txtlogpass );
        btnlogin = (Button) findViewById ( R.id.btnlogin );
        txttyporeg2 = (TextView) findViewById ( R.id.txttyporeg2 );



                sp = getSharedPreferences ( "login",MODE_PRIVATE );
        if (sp.getBoolean("Logged",false)){

            goToMainActivity ();
        }

        btnlogin.setOnClickListener ( new View.OnClickListener (){
            @Override
            public void onClick(View v){

                Login ();

            }
        } );




        txttyporeg2.setOnClickListener ( new View.OnClickListener (){
            @Override
            public void onClick(View v){
                Intent i = new Intent(LogIn.this,Register.class );
                startActivity(i);
            }
        } );



    }


    public void goToMainActivity(){
        Intent i = new Intent ( this, MainMenu.class);
        startActivity ( i );
    }



    public void Login()
    {

        OrdaringDB db = new OrdaringDB ();
        Connection con;
        con=db.connectionclass ();
        if(con==null)
        {  Intent emp = new Intent(LogIn.this,EmptyActivity.class );
            startActivity(emp);
            Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
        }
        else
        {
            try {
                Statement st = con.createStatement ();
                String query = "select * from Users where Us_Username = '"+txtuser.getText ()+"' and Us_Password = '"+txtpass.getText ()+"'";
                ResultSet r = st.executeQuery ( query );

                if(r.next ()) {
                    sp.edit().putBoolean("Logged",true).apply();
                    SharedPreferences.Editor editorus = getSharedPreferences ( user,MODE_PRIVATE ).edit ();
                    editorus.putString ( "Us_Name",r.getString ( "Us_Name" ) );
                    editorus.commit ();
                    SharedPreferences.Editor editorem = getSharedPreferences ( email,MODE_PRIVATE ).edit ();
                    editorem.putString ( "Us_Email",r.getString ( "Us_Email" ) );
                    editorem.commit ();
                    SharedPreferences.Editor editorusna = getSharedPreferences ( usname,MODE_PRIVATE ).edit ();
                    editorusna.putString ( "Us_Username",r.getString ( "Us_Username" ) );
                    editorusna.commit ();
                    Intent i = new Intent ( getApplicationContext (), MainMenu.class );
                    startActivity ( i );
                }
                else
                    Toast.makeText ( getApplication (),"Invalid Username or Password",Toast.LENGTH_LONG).show ();

            } catch (SQLException e) {
                e.printStackTrace ();
            }

        }

    }

    //finish acticity on device back buttom
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

   }
