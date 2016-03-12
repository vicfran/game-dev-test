package es.npatarino.android.gotchallenge.observer;

public interface GoTObserver {

    void register();
    void update(GoTEvent event);
}
