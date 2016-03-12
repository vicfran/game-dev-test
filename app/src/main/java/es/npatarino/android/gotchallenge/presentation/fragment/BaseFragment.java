package es.npatarino.android.gotchallenge.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import es.npatarino.android.gotchallenge.GoTChallengueApplication;
import es.npatarino.android.gotchallenge.observer.GoTEvent;
import es.npatarino.android.gotchallenge.observer.GoTObserver;

public abstract class BaseFragment extends Fragment implements GoTObserver {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        register();
    }

    @Override
    public void register() {
        GoTChallengueApplication.getBus().register(this);
    }

    @Override
    public abstract void update(GoTEvent event);
}
