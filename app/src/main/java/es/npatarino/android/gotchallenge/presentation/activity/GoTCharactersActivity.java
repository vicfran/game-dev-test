package es.npatarino.android.gotchallenge.presentation.activity;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.interactor.GoTInteractor;
import es.npatarino.android.gotchallenge.presentation.adapter.GoTCharactersAdapter;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;
import es.npatarino.android.gotchallenge.presentation.model.mapper.GoTCharacterModelMapper;

public class GoTCharactersActivity extends BaseActivity {

    private static final String TAG = "GoTCharactersActivity";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar)
    ContentLoadingProgressBar mProgressBar;

    private GoTCharactersAdapter mAdapter;

    private List<GoTCharacterModel> mCharacters;
    private GoTCharacter.GoTHouse mHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        ButterKnife.bind(this);

        final String id = getIntent().getStringExtra("id");
        final String name = getIntent().getStringExtra("name");
        final String imageUrl = getIntent().getStringExtra("imageUrl");

        mToolbar.setTitle(name);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mHouse = new GoTCharacter.GoTHouse(id, name, imageUrl);

        mAdapter = new GoTCharactersAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        update();
    }

    private void update() {
        mCharacters = GoTCharacterModelMapper.transform(GoTInteractor.getCharactersOfHouse(mHouse));

        if (mCharacters == null)
            return;

        mAdapter.update(mCharacters);

        mAdapter.notifyDataSetChanged();
        mProgressBar.hide();
    }
}