package com.map.joseph.ordaringonline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import static com.map.joseph.ordaringonline.LogIn.user;
import static com.map.joseph.ordaringonline.LogIn.usname;
import static com.map.joseph.ordaringonline.ProductActivity.price;
import static com.map.joseph.ordaringonline.ProductActivity.proname;
import static com.map.joseph.ordaringonline.ProductActivity.pronum;
import static com.map.joseph.ordaringonline.ProductActivity.quantity;
import static com.map.joseph.ordaringonline.ProductActivity.total;
import static com.map.joseph.ordaringonline.SelectMarketActivity.selectMarketName;
import static com.map.joseph.ordaringonline.SelectMarketActivity.selectmarket;

public class BasketActivity extends AppCompatActivity {

    TextView txtshopname,txtpronam,txtquantity,txtitempric,txttottal;
    Button btnsent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_basket );

        setTitle ( proname );

        txtshopname=(TextView) findViewById ( R.id.txtshopname );
        txtpronam =(TextView) findViewById ( R.id.txtpronam );
        txtquantity =(TextView) findViewById ( R.id.txtquantity );
        txtitempric =(TextView) findViewById ( R.id.txtitempric );
        txttottal =(TextView) findViewById ( R.id.txttottal );
        btnsent =(Button) findViewById ( R.id.btnsent );


        txtshopname.setText ( selectMarketName );
        txtpronam.setText ( proname );
        txtquantity.setText ( quantity );
        txtitempric.setText ( price.toString () );
        txttottal.setText ( total.toString () );

        Date currentTime = Calendar.getInstance ().getTime ();

        btnsent.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                OrdaringDB db=new OrdaringDB ();
                Connection conn= db.connectionclass ();

                if (conn==null)
                    Toast.makeText ( getApplication (),"Please Check Your Internet Access",Toast.LENGTH_LONG ).show ();

                else {
                    try {
                        Statement stm = conn.createStatement ();
                        SharedPreferences editorusna = getSharedPreferences ( usname,MODE_PRIVATE );
                        String user = editorusna.getString ("Us_Username",null);

                            int z = stm.executeUpdate ( "insert into Orders (Quantity,DeliveryStatus,OrderDate,UserName,Sh_Nam,pro_Num,Tottal,item_price) values('"+quantity.toString ()+"','Not Delivered', GETDATE() ,'"+user+"','" +selectmarket + "','"+pronum+"','"+total.toString ()+"','"+price.toString ()+"')" );
                            if (z >= 1) {
                                Toast.makeText ( getApplication (), "Your Order Has Been Send Successful..!", Toast.LENGTH_LONG ).show ();
                            }
                            else
                                Toast.makeText ( getApplication (), "Not Send..!", Toast.LENGTH_LONG ).show ();
                    } catch (SQLException e) {
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
