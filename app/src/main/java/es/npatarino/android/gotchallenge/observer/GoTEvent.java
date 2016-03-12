package es.npatarino.android.gotchallenge.observer;

public abstract class GoTEvent {

    public static final int CHARACTERS_UPDATE = 0;

    private int mType;

    public GoTEvent() {
        mType = getType();
    }

    public abstract int getType();
}
