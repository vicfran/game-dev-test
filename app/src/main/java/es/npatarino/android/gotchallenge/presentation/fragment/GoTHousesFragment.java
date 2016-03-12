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
import es.npatarino.android.gotchallenge.presentation.adapter.GoTHousesAdapter;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacter;

public class GoTHousesFragment extends Fragment {

    private static final String TAG = "GoTHousesFragment";

    public GoTHousesFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        final ContentLoadingProgressBar progressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        final GoTHousesAdapter housesAdapter = new GoTHousesAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(housesAdapter);

        new Thread(new Runnable() {

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
                    Type listType = new TypeToken<ArrayList<GoTCharacter>>() {
                    }.getType();
                    final List<GoTCharacter> characters = new Gson().fromJson(response.toString(), listType);
                    GoTHousesFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<GoTCharacter.GoTHouse> houses = new ArrayList<GoTCharacter.GoTHouse>();
                            for (int i = 0; i < characters.size(); i++) {
                                boolean houseExists = false;
                                for (int j = 0; j < houses.size(); j++) {
                                    if (houses.get(j).name.equalsIgnoreCase(characters.get(i).name)) {
                                        houseExists = true;
                                    }
                                }
                                if (!houseExists) {
                                    if (characters.get(i).imageUrl != null && !characters.get(i).imageUrl.isEmpty()) {
                                        GoTCharacter.GoTHouse h = new GoTCharacter.GoTHouse();
                                        h.id = characters.get(i).houseId;
                                        h.name = characters.get(i).name;
                                        h.imageUrl = characters.get(i).houseUrl;
                                        houses.add(h);
                                        houseExists = false;
                                    }
                                }
                            }
                            housesAdapter.addAll(houses);
                            housesAdapter.notifyDataSetChanged();
                            progressBar.hide();
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }).start();
        return rootView;
    }
}
