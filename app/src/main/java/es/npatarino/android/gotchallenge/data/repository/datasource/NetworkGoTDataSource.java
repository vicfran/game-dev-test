package es.npatarino.android.gotchallenge.data.repository.datasource;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.data.entity.GoTCharacterEntity;
import es.npatarino.android.gotchallenge.data.entity.mapper.GoTCharacterEntityJsonMapper;

/**
 * This class represents a {@link GoTDataSource} using network connection
 * for data retrieval
 */
public class NetworkGoTDataSource implements GoTDataSource {

    private static final String TAG = "NetworkGoTDataSource";

    private static final String BASE_URL =
            "http://ec2-52-18-202-124.eu-west-1.compute.amazonaws.com:3000/characters";

    private GoTCharacterEntityJsonMapper mMapper;

    public NetworkGoTDataSource() {
        mMapper = new GoTCharacterEntityJsonMapper();
    }

    @Override
    public List<GoTCharacterEntity> characterEntities() {
        return getCharactersFromService();
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

                    List<GoTCharacterEntity> entities = mMapper.transformList(response.toString());
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }


            }
        }).start();

        return new ArrayList<>();
    }
}
