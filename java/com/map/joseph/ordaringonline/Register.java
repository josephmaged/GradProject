package com.map.joseph.ordaringonline;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Register extends AppCompatActivity {

    EditText txtname,txtphone,txtpassword,txtemail,txtusername,txtaddress;
    Button btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_register );




        txtname=(EditText)findViewById ( R.id.txtname );
        txtphone=(EditText)findViewById ( R.id.txtphone );
        txtpassword=(EditText)findViewById ( R.id.txtpassword );
        txtemail=(EditText)findViewById ( R.id.txtemail );
        txtusername=(EditText)findViewById ( R.id.txtusername );
        txtaddress=(EditText)findViewById ( R.id.txtaddress );

        btnreg=(Button)findViewById ( R.id.btnreg );
        btnreg.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                OrdaringDB db=new OrdaringDB ();
                Connection conn= db.connectionclass ();

                if (conn==null)
                    Toast.makeText ( getApplication (),"Please Check Your Internet Access",Toast.LENGTH_LONG ).show ();

                else {
                    try {
                        Statement stm = conn.createStatement ();
                    if (txtname.getText ().toString ().isEmpty ())
                        txtname.setError ( "Please enter Name.." );
                    if (txtphone.getText ().toString ().isEmpty ())
                        txtphone.setError ( "Please enter Phone.." );
                    if (txtemail.getText ().toString ().isEmpty ())
                        txtemail.setError ( "Please enter your Email.." );
                    if (txtaddress.getText ().toString ().isEmpty ())
                        txtaddress.setError ( "Please enter your Address.." );
                    if (txtusername.getText ().toString ().isEmpty ())
                        txtusername.setError ( "Please enter Username.." );
                    if (txtpassword.getText ().toString ().isEmpty ())
                        txtpassword.setError ( "Please enter Password.." );
                    else {
                        int z = stm.executeUpdate ( "insert into Users (Us_Name,Us_Phone,Us_Email,Us_Address,Us_Username,Us_Password) values('" + txtname.getText () + "','" + txtphone.getText () + "','" + txtemail.getText () + "','" + txtaddress.getText () + "','" + txtusername.getText () + "','" + txtpassword.getText () + "')" );
                        if (z >= 1) {
                            Toast.makeText ( getApplication (), "Your Registration Has Been Successful..!", Toast.LENGTH_LONG ).show ();
                            Intent i = new Intent ( getApplicationContext (), LogIn.class );
                            startActivity ( i );
                        }
                        else
                            Toast.makeText ( getApplication (), "No Registration..!", Toast.LENGTH_LONG ).show ();
                    }
                    } catch (SQLException e) {
                        if (e.getMessage ().contains ( "PK_Users" ))
                            Toast.makeText ( getApplication (),"This Username is already exists",Toast.LENGTH_LONG ).show ();
                        else
                        Toast.makeText ( getApplication (),"Error is "+ e.getMessage (),Toast.LENGTH_LONG ).show ();

                    }
                }
            }
        } );
        //backbutton
        if(getSupportActionBar ()!=null){
            getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
            getSupportActionBar ().setDisplayShowHomeEnabled ( true );
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                finish ();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
