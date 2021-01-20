package com.map.joseph.ordaringonline;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Joseph on 3/10/2018.
 */

public class OrdaringDB {
    @SuppressLint("NewApi")
    public Connection connectionclass() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
        StrictMode.setThreadPolicy ( policy );

        Connection connection = null;

        try {
            Class.forName ( "net.sourceforge.jtds.jdbc.Driver" );
            connection = DriverManager.getConnection ( "jdbc:jtds:sqlserver://SQL6001.site4now.net/DB_A39573_onlineshoping", "DB_A39573_onlineshoping_admin", "adminordaring123" );
        } catch (SQLException se) {
            Log.e ( "Error Database 1 : ", se.getMessage () );
        } catch (ClassNotFoundException e) {
            Log.e ( "Error Driver 2 :", e.getMessage () );
        } catch (Exception e) {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
