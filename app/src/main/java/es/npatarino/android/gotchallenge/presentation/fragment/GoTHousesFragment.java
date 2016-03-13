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
import es.npatarino.android.gotchallenge.presentation.adapter.GoTHousesAdapter;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;
import es.npatarino.android.gotchallenge.presentation.model.mapper.GoTHouseModelMapper;

public class GoTHousesFragment extends BaseFragment {

    private static final String TAG = "GoTHousesFragment";

    private GoTHousesAdapter adapter;
    private ContentLoadingProgressBar progressBar;

    public GoTHousesFragment() {}

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        progressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        adapter = new GoTHousesAdapter(getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
    }

    @Override
    @Subscribe
    public void update(GoTEvent event) {
        if (event.getType() == GoTEvent.CHARACTERS_UPDATE)
            refresh();
    }

    private void refresh() {
        List<GoTCharacterModel.GoTHouseModel> houses = GoTHouseModelMapper.transform(GoTInteractor.getHouses());

        if (houses == null)
            return;

        adapter.addAll(houses);

        adapter.notifyDataSetChanged();
        progressBar.hide();
    }
}
