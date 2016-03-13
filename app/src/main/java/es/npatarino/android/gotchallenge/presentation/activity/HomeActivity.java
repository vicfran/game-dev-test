package es.npatarino.android.gotchallenge.presentation.activity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.presentation.adapter.HomePagerAdapter;

public class HomeActivity extends BaseActivity {

    HomePagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    Toolbar mToolbar;
    TabLayout mTabLayout;

    private SearchManager mSearchManager;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setPagerAdapter(new HomePagerAdapter(getSupportFragmentManager()));

        setViewPager((ViewPager) findViewById(R.id.container));
        getViewPager().setAdapter(getPagerAdapter());

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(getViewPager());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        initSearchView(menu, searchItem);

        return true;
    }

    public HomePagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    public void setPagerAdapter(HomePagerAdapter pagerAdapter) {
        this.mPagerAdapter = pagerAdapter;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    private void initSearchView(Menu menu, final MenuItem menuItem) {
        if ((menu == null) || (menuItem == null))
            return;

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(mSearchManager.getSearchableInfo(
                new ComponentName(getApplicationContext(), GoTSearchActivity.class)));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String arg0) {
                return false;
            }

            public boolean onQueryTextSubmit(String arg0) {
                if (menuItem != null)
                    menuItem.collapseActionView();

                return false;
            }
        });
    }

}
