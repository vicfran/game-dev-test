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
import es.npatarino.android.gotchallenge.presentation.adapter.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacter;

public class GoTHousesListFragment extends Fragment {

    private static final String TAG = "GoTHousesListFragment";

    public GoTHousesListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        final ContentLoadingProgressBar pb = (ContentLoadingProgressBar) rootView.findViewById(R.id.pb);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);

        final GoTHouseAdapter adp = new GoTHouseAdapter(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adp);

        new Thread(new Runnable() {

            @Override
            public void run() {
                String url = "http://ec2-52-18-202-124.eu-west-1.compute.amazonaws.com:3000/characters";

                URL obj = null;
                try {
                    obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Type listType = new TypeToken<ArrayList<GoTCharacter>>() {
                    }.getType();
                    final List<GoTCharacter> characters = new Gson().fromJson(response.toString(), listType);
                    GoTHousesListFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<GoTCharacter.GoTHouse> hs = new ArrayList<GoTCharacter.GoTHouse>();
                            for (int i = 0; i < characters.size(); i++) {
                                boolean b = false;
                                for (int j = 0; j < hs.size(); j++) {
                                    if (hs.get(j).n.equalsIgnoreCase(characters.get(i).hn)) {
                                        b = true;
                                    }
                                }
                                if (!b) {
                                    if (characters.get(i).hi != null && !characters.get(i).hi.isEmpty()) {
                                        GoTCharacter.GoTHouse h = new GoTCharacter.GoTHouse();
                                        h.i = characters.get(i).hi;
                                        h.n = characters.get(i).hn;
                                        h.u = characters.get(i).hu;
                                        hs.add(h);
                                        b = false;
                                    }
                                }
                            }
                            adp.addAll(hs);
                            adp.notifyDataSetChanged();
                            pb.hide();
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
