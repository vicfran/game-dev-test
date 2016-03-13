package es.npatarino.android.gotchallenge.presentation.fragment;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.domain.interactor.GoTInteractor;
import es.npatarino.android.gotchallenge.observer.GoTEvent;
import es.npatarino.android.gotchallenge.presentation.adapter.GoTCharactersAdapter;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;
import es.npatarino.android.gotchallenge.presentation.model.mapper.GoTCharacterModelMapper;

public class GoTCharactersFragment extends BaseFragment {

    private static final String TAG = "CharactersListFragment";

    private GoTCharactersAdapter adapter;
    private ContentLoadingProgressBar progressBar;

    private List<GoTCharacterModel> mCharacters;

    public GoTCharactersFragment() {}

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        progressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        adapter = new GoTCharactersAdapter(getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        update();
    }

    @Override
    @Subscribe
    public void update(GoTEvent event) {
        if (event.getType() == GoTEvent.CHARACTERS_UPDATE)
            update();
    }

    private void update() {
        mCharacters = GoTCharacterModelMapper.transform(GoTInteractor.getCharacters());

        if (mCharacters == null)
            return;

        adapter.update(mCharacters);

        adapter.notifyDataSetChanged();
        progressBar.hide();
    }
}
