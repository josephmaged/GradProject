package com.map.joseph.ordaringonline;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.map.joseph.ordaringonline.LogIn.usname;


/**
 * A simple {@link Fragment} subclass.
 */
public class notDeliveredFragment extends Fragment {


    public notDeliveredFragment() {
        // Required empty public constructor
    }

    OrdaringDB db = new OrdaringDB ();
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    ListView lst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vlst = inflater.inflate ( R.layout.fragment_delivered,container,false );

        // listview
        lst = (ListView) vlst.findViewById ( R.id.lstOrders );
        con = db.connectionclass ();
        if (con == null) {
            Toast.makeText ( getActivity (), "Please Check Internet Connection..!", Toast.LENGTH_LONG ).show ();
        } else {
            try {
                SharedPreferences editorusna = getActivity ().getSharedPreferences ( usname,MODE_PRIVATE );
                String usname = editorusna.getString ("Us_Username",null);
                Statement st = con.createStatement ();
                String query = "select Sh_Name from Ordersview where UserName='"+usname+"' and DeliveryStatus= 'Not Delivered'";
                ResultSet rs = st.executeQuery ( query );


                ArrayList<String> data = new ArrayList<String>();
                while (rs.next()) {
                    String id = rs.getString ( "Sh_Name" );
                    data.add ( id );
                }

                String[] array = data.toArray(new String[0]);
                ArrayAdapter<String> NoCoreAdapter = new ArrayAdapter <String> (getActivity (),android.R.layout.simple_spinner_dropdown_item, data);
                lst.setAdapter(NoCoreAdapter);

            } catch (SQLException e) {
                e.printStackTrace ();
            }
        }

        return vlst;
    }

}
