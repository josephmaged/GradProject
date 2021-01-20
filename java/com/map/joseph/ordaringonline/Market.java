package com.map.joseph.ordaringonline;

/**
 * Created by Joseph on 3/21/2018.
 */

public class Market {
    String Sh_Number,Sh_Name,Deliv_Avail,MarketImage;

    public Market() {
    }

    public String getMarketImage() {
        return MarketImage;
    }

    public void setMarketImage(String image) {
        MarketImage = image;
    }

    public String getSh_Name() {
        return Sh_Name;
    }

    public void setSh_Name(String sh_Name) {
        Sh_Name = sh_Name;
    }

    public String getDeliv_Avail() {
        return Deliv_Avail;
    }

    public void setDeliv_Avail(String deliv_Avail) {
        Deliv_Avail = deliv_Avail;
    }

    public String getSh_Number() {
        return Sh_Number;
    }

    public void setSh_Number(String sh_Number) {
        Sh_Number = sh_Number;
    }


}
