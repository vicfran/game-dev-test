package es.npatarino.android.gotchallenge.presentation.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.presentation.adapter.SectionsPagerAdapter;

public class HomeActivity extends AppCompatActivity {

    SectionsPagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    Toolbar mToolbar;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setPagerAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        setViewPager((ViewPager) findViewById(R.id.container));
        getViewPager().setAdapter(getPagerAdapter());

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(getViewPager());
    }

    public SectionsPagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    public void setPagerAdapter(SectionsPagerAdapter pagerAdapter) {
        this.mPagerAdapter = pagerAdapter;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }
}
