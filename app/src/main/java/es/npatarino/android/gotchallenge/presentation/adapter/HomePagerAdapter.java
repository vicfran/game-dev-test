package es.npatarino.android.gotchallenge.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import es.npatarino.android.gotchallenge.presentation.fragment.GoTHousesFragment;
import es.npatarino.android.gotchallenge.presentation.fragment.GoTCharactersFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int page) {
        if (page == 0) {
            return new GoTCharactersFragment();
        } else {
            return new GoTHousesFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int page) {
        switch (page) {
            case 0:
                return "Characters";
            case 1:
                return "Houses";
        }
        return null;
    }
}
