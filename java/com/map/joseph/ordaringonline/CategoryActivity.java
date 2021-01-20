package com.map.joseph.ordaringonline;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    OrdaringDB db = new OrdaringDB ();
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    ListView lst;
    public static String categoryitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_category );

        //listview
        lst = (ListView) findViewById(R.id.lstcategory);
        con= db.connectionclass ();
        if (con==null){
            Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
        }
        else
        {
            try {
                Statement st = con.createStatement ();
                String query = "select Cat_Name from Category";
                ResultSet rs = st.executeQuery ( query );

                ArrayList<String> data = new ArrayList<String>();
                while (rs.next()) {
                    String id = rs.getString ( "Cat_Name" );
                    data.add ( id );
                }

                String[] array = data.toArray(new String[0]);
                ArrayAdapter<String> NoCoreAdapter = new ArrayAdapter <> (this,android.R.layout.simple_spinner_dropdown_item, data);
                lst.setAdapter(NoCoreAdapter);

            } catch (SQLException e) {
                e.printStackTrace ();
            }

            lst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener () {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String name = lst.getSelectedItem().toString();
                }
                @Override
                public void onNothingSelected(AdapterView <?> adapterView) {
                }
            });
            lst.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
                @Override
                public void onItemClick(AdapterView <?> adapterView, View view, int position, long l) {

                    con= db.connectionclass ();
                    if (con==null){
                        Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
                    }
                    else {
                        categoryitem = (String) (lst.getItemAtPosition ( position ));
                        startActivity ( new Intent ( CategoryActivity.this, SelectMarketActivity.class ) );
                    }
                }
            } );

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
