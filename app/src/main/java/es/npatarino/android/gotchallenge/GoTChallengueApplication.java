package es.npatarino.android.gotchallenge;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.orm.SugarContext;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class GoTChallengueApplication extends Application {

    private static Context sContext;
    private static Bus sBus;
    private static Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
        sBus = new Bus(ThreadEnforcer.ANY);
        sHandler = new Handler();

        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        SugarContext.terminate();
    }

    public static Context getContext() {
        return sContext;
    }

    public static Bus getBus() {
        return sBus;
    }

    public static Handler getMainHandler() {
        return sHandler;
    }
}
