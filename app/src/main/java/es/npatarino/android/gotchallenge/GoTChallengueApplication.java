package es.npatarino.android.gotchallenge;

import android.app.Application;
import android.content.Context;

public class GoTChallengueApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }
}
