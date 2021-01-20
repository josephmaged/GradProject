package com.map.joseph.ordaringonline;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Joseph on 3/21/2018.
 */


public class getMarket {
    OrdaringDB db = new OrdaringDB ();
    public List<Market> GetMarketData(Context  cont)
    {
        List<Market> marketdata = new ArrayList <Market> (  );
        Connection conn = db.connectionclass ();
        if(conn == null) {
            Toast.makeText ( cont, "Check Internet Access", Toast.LENGTH_LONG ).show ();
        } else
        {
            try {
                Statement st = conn.createStatement ();
                ResultSet rmarket = st.executeQuery ( "Select  Sh_Name,Deliv_Avail,Sh_Number,Img_URL from Shop where AreaNam ='"+SelectAreaActivity.areaitem+"' and Cat_Nam ='"+CategoryActivity.categoryitem+"'" );
                while (rmarket.next ())
                {
                    Market clas = new Market ();
                    clas.setSh_Name ( rmarket.getString ( 1 ) );
                    clas.setDeliv_Avail ( rmarket.getString ( 2 ) );
                    clas.setMarketImage ( rmarket.getString ( 4 ) );
                    clas.setSh_Number ( rmarket.getString ( 3 ) );
                    marketdata.add ( clas );
                    //Toast.makeText ( cont,SelectAreaActivity.areaitem , Toast.LENGTH_SHORT ).show ();
                }
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        }
        return marketdata;
    }
}
