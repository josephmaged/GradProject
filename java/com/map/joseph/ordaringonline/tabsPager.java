package com.map.joseph.ordaringonline;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by Joseph on 4/12/2018.
 */

public class tabsPager extends FragmentStatePagerAdapter {

    String[] titles= new String[] {"Not Delivered","Under Preparing","Delivered"};

    public tabsPager(FragmentManager fm) {
        super ( fm );
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                notDeliveredFragment notDeliveredFragment = new notDeliveredFragment ();
                return notDeliveredFragment;

            case 1:
                UnderPreparingFragment UnderPreparingFragment = new UnderPreparingFragment();
                return UnderPreparingFragment;

            case 2:
                DeliveredFragment DeliveredFragment = new DeliveredFragment();
                return DeliveredFragment;

        }
        return null;
    }

    @Override
    public int getCount() {

        return 3;
    }
}
