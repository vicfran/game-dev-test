package es.npatarino.android.gotchallenge.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.domain.interactor.GoTInteractor;
import es.npatarino.android.gotchallenge.presentation.adapter.GoTCharactersAdapter;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;
import es.npatarino.android.gotchallenge.presentation.model.mapper.GoTCharacterModelMapper;

public class GoTCharactersFragment extends Fragment {

    private static final String TAG = "CharactersListFragment";

    public GoTCharactersFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        final ContentLoadingProgressBar progressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        final GoTCharactersAdapter adapter = new GoTCharactersAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // TODO : just for testing the scaffolding, remove later
        adapter.addAll(GoTCharacterModelMapper.transform(GoTInteractor.getCharacters()));

        /*new Thread(new Runnable() {

            @Override
            public void run() {
                String urlString = "http://ec2-52-18-202-124.eu-west-1.compute.amazonaws.com:3000/characters";

                URL url = null;
                try {
                    url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = reader.readLine()) != null) {
                        response.append(inputLine);
                    }
                    reader.close();

                    Type listType = new TypeToken<ArrayList<GoTCharacterModel>>() {
                    }.getType();
                    final List<GoTCharacterModel> characters = new Gson().fromJson(response.toString(), listType);
                    GoTCharactersFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addAll(characters);
                            adapter.notifyDataSetChanged();
                            progressBar.hide();
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }


            }
        }).start();*/
        return rootView;
    }
}
