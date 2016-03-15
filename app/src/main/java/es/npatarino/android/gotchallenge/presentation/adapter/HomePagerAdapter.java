package es.npatarino.android.gotchallenge.presentation.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.presentation.fragment.GoTHousesFragment;
import es.npatarino.android.gotchallenge.presentation.fragment.GoTCharactersFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private static final int PAGES = 2;

    private static final int CHARACTERS_PAGE = 0;
    private static final int HOUSES_PAGE = 1;

    private Context mContext;

    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        mContext = context;
    }

    @Override
    public Fragment getItem(int page) {
        switch (page) {
            case HOUSES_PAGE:
                return new GoTHousesFragment();
            case CHARACTERS_PAGE:
            default:
                return new GoTCharactersFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGES;
    }

    @Override
    public CharSequence getPageTitle(int page) {
        switch (page) {
            case HOUSES_PAGE:
                return mContext.getString(R.string.houses);
            case CHARACTERS_PAGE:
            default:
                return mContext.getString(R.string.characters);
        }
    }
}
