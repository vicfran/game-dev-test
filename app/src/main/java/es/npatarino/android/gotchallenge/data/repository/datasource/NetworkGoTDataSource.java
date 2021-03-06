package es.npatarino.android.gotchallenge.data.repository.datasource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.squareup.otto.Bus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.observer.GoTCharactersUpdate;
import es.npatarino.android.gotchallenge.GoTChallengueApplication;
import es.npatarino.android.gotchallenge.observer.GoTObservable;
import es.npatarino.android.gotchallenge.data.entity.GoTCharacterEntity;
import es.npatarino.android.gotchallenge.data.entity.mapper.GoTCharacterEntityJsonMapper;

/**
 * This class represents a {@link GoTDataSource} using network connection
 * for data retrieval
 */
public class NetworkGoTDataSource implements GoTDataSource, GoTObservable {

    private static final String TAG = "NetworkGoTDataSource";

    private static final String BASE_URL =
            "http://ec2-52-18-202-124.eu-west-1.compute.amazonaws.com:3000/characters";

    private static NetworkGoTDataSource INSTANCE = null;

    private static final Context sContext =
            GoTChallengueApplication.getContext();

    private static final Bus sBus =
            GoTChallengueApplication.getBus();

    private List<GoTCharacterEntity> mCharacters;

    private NetworkGoTDataSource() {
        mCharacters = new ArrayList<>();
    }

    public static NetworkGoTDataSource getInstance() {
        if (INSTANCE == null)
            INSTANCE = new NetworkGoTDataSource();

        return INSTANCE;
    }

    @Override
    public List<GoTCharacterEntity> characterEntities() {
        return getCharactersFromService();
    }

    @Override
    public void notifyEvent() {
        // TODO : find a better approach to do this, needs to be encapsulated
        GoTChallengueApplication.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                sBus.post(new GoTCharactersUpdate());
            }
        });
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) sContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return ((networkInfo != null) &&
                (networkInfo.isConnected()));
    }

    private List<GoTCharacterEntity> getCharactersFromService() {
        // TODO : manage network connection in a better way
        // This piece of code is legacy from old GoTListFragment
        new Thread(new Runnable() {

            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(BASE_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = reader.readLine()) != null) {
                        response.append(inputLine);
                    }
                    reader.close();

                    mCharacters = GoTCharacterEntityJsonMapper.transformList(response.toString());

                    GoTCharacterEntity.deleteAll(GoTCharacterEntity.class);
                    GoTCharacterEntity.saveInTx(mCharacters);
                    CacheGoTPolicy.updateLastAccessTime();

                    notifyEvent();
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }


            }
        }).start();

        return mCharacters;
    }
}
