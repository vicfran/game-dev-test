package es.npatarino.android.gotchallenge.data.repository.datasource;

import android.content.Context;
import android.content.SharedPreferences;

import es.npatarino.android.gotchallenge.GoTChallengueApplication;

/**
 * This class encapsulates some related cache info
 */
public final class CacheGoTPolicy {

    private static final long MAX_EXPIRATION_TIME = 5 * 60 * 1000;
    private static final String LAST_ACCESS_TIME_KEY = "last_access";
    private static final String PREFERENCES_NAME = "GoTChallengueApplication";

    private static final SharedPreferences sPreferences =
            GoTChallengueApplication.getContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    private static final SharedPreferences.Editor sEditor = sPreferences.edit();

    private CacheGoTPolicy() {}

    public static void updateLastAccessTime() {
        sEditor.putLong(LAST_ACCESS_TIME_KEY, System.currentTimeMillis()).apply();
    }

    public static long getLastAccessTime() {
        return sPreferences.getLong(LAST_ACCESS_TIME_KEY, 0);
    }

    public static boolean isCacheExpired() {
        long now = System.currentTimeMillis();
        long last = getLastAccessTime();

        return ((now - last) > MAX_EXPIRATION_TIME);
    }
}
