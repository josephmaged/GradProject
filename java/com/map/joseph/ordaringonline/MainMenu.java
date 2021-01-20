package com.map.joseph.ordaringonline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;

import static com.map.joseph.ordaringonline.CategoryActivity.categoryitem;
import static com.map.joseph.ordaringonline.LogIn.email;
import static com.map.joseph.ordaringonline.LogIn.user;
import static com.map.joseph.ordaringonline.SelectAreaActivity.areaitem;
import static com.map.joseph.ordaringonline.SelectCityActivity.cityitem;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SharedPreferences sp;
    TextView navUsername;
    TextView navEmail;
    TextView txtarea;
    TextView txtcity;
    Button btnfind;

    ConstraintLayout conslayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main_menu );
        Toolbar toolbar = (Toolbar) findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        conslayout = (ConstraintLayout) findViewById ( R.id.conslayout ) ;
        conslayout.getBackground().setAlpha(40);

        OrdaringDB db = new OrdaringDB ();
        final Connection con;
        con=db.connectionclass ();
        if(con==null)
        {  Intent emp = new Intent(MainMenu.this,EmptyActivity.class );
            startActivity(emp);
            Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
        }else {


        setUserName ();
        setEmail ();

        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener ( toggle );
        toggle.syncState ();

        NavigationView navigationView = (NavigationView) findViewById ( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener ( this );


        txtarea = (TextView) findViewById ( R.id.txtarea );
        txtarea.setOnClickListener ( new View.OnClickListener (){
            @Override
            public void onClick(View v){
                OrdaringDB db = new OrdaringDB ();
                Connection con;
                con= db.connectionclass ();
                if (con==null){
                    Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
                }
                else {
                    if (cityitem == null) {
                        Toast.makeText ( MainMenu.this, "Please Select City at first..", Toast.LENGTH_SHORT ).show ();
                    } else {

                        Intent i = new Intent ( MainMenu.this, SelectAreaActivity.class );
                        startActivity ( i );
                    }
                }
            }
        } );
        txtcity = (TextView) findViewById ( R.id.txtcity );
        txtcity.setOnClickListener ( new View.OnClickListener (){
            @Override
            public void onClick(View v){
                OrdaringDB db = new OrdaringDB ();
                Connection con;
                con= db.connectionclass ();
                if (con==null){
                    Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
                }
                else {
                    Intent i = new Intent ( MainMenu.this, SelectCityActivity.class );
                    startActivity ( i );
                }

            }
        } );
        btnfind = (Button) findViewById ( R.id.btnfind );
        btnfind.setOnClickListener ( new View.OnClickListener (){
            @Override
            public void onClick(View v){
                OrdaringDB db = new OrdaringDB ();
                Connection con;
                con= db.connectionclass ();
                if (con==null){
                    Toast.makeText ( getApplication (),"Please Check Internet Connection..!",Toast.LENGTH_LONG ).show ();
                }
                else {
                    if (areaitem == null && categoryitem == null) {
                        Toast.makeText ( MainMenu.this, "Please Select City at first..", Toast.LENGTH_SHORT ).show ();
                    } else {
                        Intent i = new Intent ( MainMenu.this, SelectMarketActivity.class );
                        startActivity ( i );
                    }
                }
            }
        } );


    }}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate ( R.menu.main_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_logout)
        {

            SharedPreferences preferences =getSharedPreferences("login",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit ();
            finish();
            Intent i = new Intent(getApplicationContext(), LogIn.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected ( item );
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId ();

        if (id == R.id.nav_home) {
            Intent i = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(i);

        } else if (id == R.id.nav_cart) {
            Intent i = new Intent(getApplicationContext(), MainOrdersActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_offers) {
            Intent i = new Intent(getApplicationContext(), MainOffersActivity.class);
            startActivity(i);

        } else if (id == R.id.livechat) {
            Intent i = new Intent(getApplicationContext(), MainChatActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(getApplicationContext(), MainShareActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_about) {
            Intent i = new Intent(getApplicationContext(), MainAboutUsActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        drawer.closeDrawer ( GravityCompat.START );
        return true;
    }

//Click back button twice to exit
boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            // make sure you have this outcommented
            // super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;

            }
        }, 3000);
    }

    //start nav menu texts
    public void setUserName (){
        NavigationView navigationView=(NavigationView) findViewById ( R.id.nav_view );
        View headerView = navigationView.getHeaderView ( 0 );
        navUsername = (TextView) headerView.findViewById ( R.id.txtnav_User );
        SharedPreferences us = getSharedPreferences ( user,MODE_PRIVATE );
        String user = us.getString ("Us_Name",null).toUpperCase();
        navUsername.setText ( user );
    }

    public void setEmail (){
        NavigationView navigationView=(NavigationView) findViewById ( R.id.nav_view );
        View headerView = navigationView.getHeaderView ( 0 );
        navEmail = (TextView) headerView.findViewById ( R.id.txtnav_Email );
        SharedPreferences em = getSharedPreferences ( email,MODE_PRIVATE );
        String email = em.getString ( "Us_Email",null);
        navEmail.setText ( email );
    }
    //end nav menu texts

}
