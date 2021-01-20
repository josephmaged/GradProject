package com.map.joseph.ordaringonline;

/**
 * Created by Joseph on 3/21/2018.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.media.Image;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class dataAdapter extends ArrayAdapter<Market> {

    Context context;
    ArrayList<Market> mcontact;

    public dataAdapter(Context context, ArrayList<Market> contact){
        super(context, R.layout.listcontent, contact);

        this.context=context;
        this.mcontact=contact;
    }

    public static class  Holder{

        TextView Sh_Name,Deliv_Avail,Sh_Number;
        ImageView MarketImage;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Market data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {


            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.listcontent, parent, false);

            viewHolder.Sh_Name = (TextView) convertView.findViewById(R.id.txtshop);
            viewHolder.Deliv_Avail = (TextView) convertView.findViewById(R.id.txtstatus);
            viewHolder.MarketImage = (ImageView) convertView.findViewById ( R.id.imgshops  );




            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }



        PicassoClient.downloadImage(context,mcontact.get(position).getMarketImage(),viewHolder.MarketImage);

        viewHolder.Sh_Name.setText(data.getSh_Name ());
        viewHolder.Deliv_Avail.setText(data.getDeliv_Avail ());

      /*  if(data.getImgstock()!=null) {
            viewHolder.pic.setImageBitmap(convertToBitmap( data.getImgstock()));
        }
        else
        {
            String uri = "@drawable/bookk";
            viewHolder.pic.setImageBitmap(convertToBitmap(uri));

        }
*/

        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array

    private Bitmap convertToBitmap(String b){

        byte[] decodeString = Base64.decode(b, Base64.DEFAULT);
        Bitmap decodebitmap = BitmapFactory.decodeByteArray(
                decodeString, 0, decodeString.length);

        return  decodebitmap;

    }

}
