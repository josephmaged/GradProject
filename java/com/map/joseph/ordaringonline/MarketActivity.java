package com.map.joseph.ordaringonline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.map.joseph.ordaringonline.SelectMarketActivity.selectMarketName;
import static com.map.joseph.ordaringonline.SelectMarketActivity.selectmarket;

public class MarketActivity extends AppCompatActivity {

    TextView txtMarket,txtCategory,txtmin,txtdev;
    ImageView imgmarket;
    List<String> productdata=new ArrayList<String>();
    OrdaringDB db = new OrdaringDB ();
    Connection con;
    ArrayAdapter<String> adapter;
    ListView lstViewproduct;

    public static String product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_market );

        txtMarket = (TextView) findViewById ( R.id.txtMarket );
        txtCategory = (TextView) findViewById ( R.id.txtCategory );
        txtmin = (TextView) findViewById ( R.id.txtmin );
        txtdev = (TextView) findViewById ( R.id.txtdev );
        imgmarket = (ImageView) findViewById ( R.id.imgmarket );
        lstViewproduct = (ListView) findViewById ( R.id.lstproduc );

        setTitle ( selectMarketName );
        con= db.connectionclass ();


        if (con==null){
            Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
        }else {
            try {
                Statement st = con.createStatement ();
                ResultSet rmarket = st.executeQuery ( "select * from Shop where Sh_Number ='"+selectmarket+"'" );
                if (rmarket.next ())
                {
                    txtMarket.setText ( rmarket.getString ( 2 ) );
                    txtmin.setText ( rmarket.getString ( 9 ) );
                    txtdev.setText ( rmarket.getString ( 10 ) );
                    txtCategory.setText ( rmarket.getString ( 6 ) );
                    Picasso.get ().load(rmarket.getString ( 8  ) ).into(imgmarket);


                }


                final ResultSet rproduct = st.executeQuery ( "select Pro_name  from Products where ShopNumber ='"+selectmarket+"'" );
                while (rproduct.next ())
                {
                    productdata.add ( rproduct.getString ( 1 ) );
                    adapter = new ArrayAdapter <String> ( this,android.R.layout.simple_spinner_dropdown_item,productdata );
                    lstViewproduct.setAdapter ( adapter );
                    lstViewproduct.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
                        @Override
                        public void onItemClick(AdapterView <?> adapterView, View view, int position, long l) {

                            SharedPreferences.Editor editorpro = getSharedPreferences ( product,MODE_PRIVATE ).edit ();
                            try {
                                editorpro.putString ( "Pro_name",rproduct.getString ( "Pro_name" ) );
                            } catch (SQLException e) {
                                e.printStackTrace ();
                            }
                            editorpro.commit ();

                            startActivity ( new Intent ( MarketActivity.this,ProductActivity.class ) );
                        }
                    } );
                }
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        }


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
