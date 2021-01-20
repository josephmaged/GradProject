package com.map.joseph.ordaringonline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.himanshusoni.quantityview.QuantityView;

import static com.map.joseph.ordaringonline.LogIn.usname;
import static com.map.joseph.ordaringonline.SelectMarketActivity.selectMarketName;
import static com.map.joseph.ordaringonline.SelectMarketActivity.selectmarket;

public class ProductActivity extends AppCompatActivity {

    OrdaringDB db = new OrdaringDB ();
    Connection con;

    TextView txtproname,txtdetails,txtprice,txttottal,txtquantity;
    Button btnplus,btnmin,btnsub;
    int minteger = 0;
    View view;
    ScrollView scr;
    FloatingActionButton flt;

    public static String proname,quantity,pronum,pronumsh;


    public static Double price,total;
    public static Integer quan;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_product );

        txtproname = (TextView) findViewById ( R.id.txtproductname );
        txtdetails = (TextView) findViewById ( R.id.txtdetails );
        txtprice = (TextView) findViewById ( R.id.txtprice );
        txttottal = (TextView) findViewById ( R.id.txttottal );
        txtquantity = (TextView) findViewById ( R.id.txtquantity );
        view = (View) findViewById ( R.id.view3 );
        btnplus = (Button)findViewById ( R.id.btnplus );
        btnmin = (Button)findViewById ( R.id.btnmin );
        btnsub = (Button)findViewById ( R.id.btnadd );
        scr = (ScrollView) findViewById ( R.id.scr );
        flt = (FloatingActionButton) findViewById ( R.id.flt );

        btnmin.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                decreaseInteger(view);


                price=Double.parseDouble ( txtprice.getText ().toString () );
                quan=minteger;
                total = price * quan;
                txttottal.setText (String.valueOf ( total ) );
            }
        } );

        btnplus.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                increaseInteger(view);


                price=Double.parseDouble ( txtprice.getText ().toString () );
                quan=minteger;
                total = price * quan;
                txttottal.setText (String.valueOf ( total ) );
            }
        } );


        setTitle ( selectMarketName );
        con= db.connectionclass ();



        if (con==null){
            Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
        }else {
            try {
                Statement st = con.createStatement ();
                ResultSet rmarket = st.executeQuery ( "select * from Products where ShopNumber ='" + selectmarket + "'" );
                if (rmarket.next ()) {
                    txtproname.setText ( rmarket.getString ( 2 ) );
                    txtdetails.setText ( rmarket.getString ( 3 ) );
                    txtprice.setText ( rmarket.getString ( 4 ) );
                    pronum = (rmarket.getString ( 1 ));

                    proname = txtproname.getText ().toString ();


                }
            }catch (SQLException e) {
                e.printStackTrace ();
            }
        }

                //backbutton
        if(getSupportActionBar ()!=null){
            getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
            getSupportActionBar ().setDisplayShowHomeEnabled ( true );
        }



        //Flouting
        //default hide
        flt.hide ();

        scr.setOnScrollChangeListener ( new View.OnScrollChangeListener () {
            @Override
            public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
                    flt.hide();
                } else {
                    flt.show();
                }
            }
        } );
       //end flouting

        btnsub.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                OrdaringDB dob=new OrdaringDB ();
                Connection coonn= dob.connectionclass ();

                if (coonn==null) {
                    Toast.makeText ( getApplication (), "Please Check Your Internet Access", Toast.LENGTH_LONG ).show ();
                }
                else {

                        if (txtquantity.getText ().equals ( "0" )) {
                            Toast.makeText ( getApplication (), "Please Select Quantity", Toast.LENGTH_LONG ).show ();
                        }else {

                            SharedPreferences editorquant = getSharedPreferences ( "qu",0 );
                            quantity = editorquant.getString ( "finalqua" ,txtquantity.getText ().toString () );

                            flt.show ();

                            Snackbar.make(view, "Ur Order Has Added To Basket" , Snackbar.LENGTH_LONG)
                                   .setAction("Action", null).show();
                        }


                }
            }
        } );

        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent ( getApplicationContext (), BasketActivity.class );
                startActivity ( i );
            }
        });
    }

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);
    }

    public void decreaseInteger(View view) {
       if (minteger == 0){
       }else {
                minteger = minteger - 1;
        display(minteger);
       }

    }

    private void display(int number) {
        txtquantity.setText("" + number);
        //quantity
        SharedPreferences editorquant = getSharedPreferences ( "qu",0 );
        SharedPreferences.Editor sedt = editorquant.edit ();
        sedt.putString ( "finalqua" ,txtquantity.getText ().toString () );
        sedt.commit ();
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
