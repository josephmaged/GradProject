package com.map.joseph.ordaringonline;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by JOSEPH on 19/04/2018.
 */

public class PicassoClient {
    static String imgs;



    public static void downloadImage(Context c, String url, ImageView img)
    {

        if(url != null && url.length()>0)
        {
            Picasso.get ().load(url).placeholder(R.drawable.shopcart).into(img);
        }
        else
        {
            Picasso.get ().load(R.drawable.shopcart).into(img);
        }
    }
}