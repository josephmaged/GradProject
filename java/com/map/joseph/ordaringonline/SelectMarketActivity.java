package com.map.joseph.ordaringonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SelectMarketActivity extends AppCompatActivity {


    OrdaringDB db = new OrdaringDB ();
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    ListView lst;
    getMarket gm = new getMarket ();
    dataAdapter data;
    public static String selectmarket=null;
    public static String selectMarketName=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_select_market );

        // listview
        lst =(ListView) findViewById ( R.id.lstmarket );
        final ArrayList<Market> contacts;
        contacts = new ArrayList <> ( gm.GetMarketData ( SelectMarketActivity.this) );
        data=new dataAdapter ( this,contacts );
        lst.setAdapter ( data );

        lst.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long l) {


                selectmarket = contacts.get ( position ).getSh_Number ();
                selectMarketName = contacts.get ( position ).getSh_Name ();

                con= db.connectionclass ();
                if (con==null){
                    Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
                }
                else {
                    // Toast.makeText ( getApplicationContext (), selectmarket,Toast.LENGTH_LONG ).show ();
                    startActivity ( new Intent ( SelectMarketActivity.this, MarketActivity.class ) );
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
