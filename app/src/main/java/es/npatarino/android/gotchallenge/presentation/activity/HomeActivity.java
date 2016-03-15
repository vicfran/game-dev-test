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

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.presentation.adapter.HomePagerAdapter;

public class HomeActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.container)
    ViewPager mViewPager;

    private HomePagerAdapter mPagerAdapter;

    private SearchManager mSearchManager;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        setSupportActionBar(mToolbar);

        mPagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        initSearchView(menu, searchItem);

        return true;
    }

    private void initSearchView(Menu menu, final MenuItem menuItem) {
        if ((menu == null) || (menuItem == null))
            return;

        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        if (mSearchView == null)
            return;

        mSearchView.setSearchableInfo(mSearchManager.getSearchableInfo(
                new ComponentName(getApplicationContext(), GoTSearchActivity.class)));
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
