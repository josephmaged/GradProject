package com.map.joseph.ordaringonline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import static com.map.joseph.ordaringonline.LogIn.email;
import static com.map.joseph.ordaringonline.LogIn.user;

public class MainChatActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView navUsername,navEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main_chat );
        Toolbar toolbar = (Toolbar) findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        setUserName ();
        setEmail ();

        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener ( toggle );
        toggle.syncState ();

        NavigationView navigationView = (NavigationView) findViewById ( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener ( this );

        
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        if (drawer.isDrawerOpen ( GravityCompat.START )) {
            drawer.closeDrawer ( GravityCompat.START );
        } else {
            super.onBackPressed ();
        }
    }

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

            SharedPreferences preferences =getSharedPreferences("login", Context.MODE_PRIVATE);
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
            // Handle the camera action
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
