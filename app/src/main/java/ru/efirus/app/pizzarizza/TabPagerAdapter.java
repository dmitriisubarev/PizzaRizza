package ru.efirus.app.pizzarizza;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by efirus on 07.03.18.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                OneFragment tab1 = new OneFragment();
                return tab1;
            case 1:
                TwoFragment tab2 = new TwoFragment();
                return tab2;
            case 2:
                ThreeFragment tab3 = new ThreeFragment();
                return tab3;
            case 3:
                FourFragment tab4 = new FourFragment();
                return tab4;
            case 4:
                FiveFragment tab5 = new FiveFragment();
                return tab5;
            case 5:
                SixFragment tab6 = new SixFragment();
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
