package com.example.testvid.Presenter.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.example.testvid.Other.Variables;
import com.example.testvid.View.Fragments.FragmentFeautered;
import com.example.testvid.View.Fragments.FragmentFeed;
import com.example.testvid.View.Fragments.FragmentLogin;
import com.example.testvid.View.Fragments.FragmentNew;


public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentFeautered feautered = new FragmentFeautered();
                return feautered;
            case 1:
                FragmentNew fragmentNew = new FragmentNew();
                return fragmentNew;
            case 2:
                if (Variables.isLogin) {
                    FragmentFeed feed = new FragmentFeed();
                    return feed;
                } else {
                    FragmentLogin login = new FragmentLogin();
                    return login;
                }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
// POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }
}
