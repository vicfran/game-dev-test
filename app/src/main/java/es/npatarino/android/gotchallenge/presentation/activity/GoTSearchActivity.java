package es.npatarino.android.gotchallenge.presentation.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.domain.interactor.GoTInteractor;
import es.npatarino.android.gotchallenge.presentation.adapter.GoTCharactersAdapter;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;
import es.npatarino.android.gotchallenge.presentation.model.mapper.GoTCharacterModelMapper;

public class GoTSearchActivity extends BaseActivity {

    private static final String TAG = "GoTSearchActivity";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar)
    ContentLoadingProgressBar mProgressBar;
    @Bind(R.id.lbl_empty)
    TextView mEmptyTextView;

    private GoTCharactersAdapter mAdapter;
    private List<GoTCharacterModel> mCharacters = new ArrayList<>();

    private String mNameQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mNameQuery = intent.getStringExtra(SearchManager.QUERY);
        }

        String title = TextUtils.isEmpty(mNameQuery) ?
                getString(R.string.action_search) :
                String.format(getString(R.string.search_result), mNameQuery);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdapter = new GoTCharactersAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        update();
    }

    private void update() {
        mCharacters = GoTCharacterModelMapper.transform(GoTInteractor.getCharactersByName(mNameQuery));

        if (mCharacters == null)
            return;

        mAdapter.update(mCharacters);

        mAdapter.notifyDataSetChanged();
        mProgressBar.hide();

        checkResults();
    }

    private void checkResults() {
        if (mProgressBar != null)
            mProgressBar.hide();

        if ((mCharacters == null) || (mCharacters.isEmpty())) {
            hideResultsView();
            showEmptyView();
        } else {
            showResultsView();
            hideEmptyView();
        }
    }

    private void hideEmptyView() {
        mEmptyTextView.setVisibility(View.GONE);
    }

    private void showEmptyView() {
        mEmptyTextView.setVisibility(View.VISIBLE);
    }

    private void hideResultsView() {
        mRecyclerView.setVisibility(View.GONE);
    }

    private void showResultsView() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
