package es.npatarino.android.gotchallenge.data.repository.datasource;

import java.util.List;

import es.npatarino.android.gotchallenge.data.entity.GoTCharacterEntity;
import es.npatarino.android.gotchallenge.observer.GoTObservable;

/**
 * This class represents a {@link GoTDataSource} using internal device
 * storage for data retrieval
 */
public class CacheGoTDataSource implements GoTDataSource, GoTObservable {

    private static CacheGoTDataSource INSTANCE = null;

    private CacheGoTDataSource() {}

    public static CacheGoTDataSource getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CacheGoTDataSource();

        return INSTANCE;
    }

    /**
     * @return a {@link List} of {@link GoTCharacterEntity} from the database
     */
    @Override
    public List<GoTCharacterEntity> characterEntities() {
        return GoTCharacterEntity.listAll(GoTCharacterEntity.class);
    }

    @Override
    public void notifyEvent() {}
}
