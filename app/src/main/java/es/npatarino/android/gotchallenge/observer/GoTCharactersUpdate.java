package es.npatarino.android.gotchallenge.observer;

public class GoTCharactersUpdate extends GoTEvent {

    @Override
    public int getType() {
        return GoTEvent.CHARACTERS_UPDATE;
    }
}
