package es.npatarino.android.gotchallenge.data.repository;

import java.util.List;

import es.npatarino.android.gotchallenge.data.entity.mapper.GoTCharacterEntityDataMapper;
import es.npatarino.android.gotchallenge.data.repository.datasource.CacheGoTDataSource;
import es.npatarino.android.gotchallenge.data.repository.datasource.CacheGoTPolicy;
import es.npatarino.android.gotchallenge.data.repository.datasource.GoTDataSource;
import es.npatarino.android.gotchallenge.data.repository.datasource.NetworkGoTDataSource;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.repository.GoTRepository;

/**
 * {@GoTRepository} used to retrieve GoT related data
 */
public class GoTDataRepository implements GoTRepository {

    private GoTDataSource mGoTDataSource;

    public GoTDataRepository() {}

    @Override
    public List<GoTCharacter> characters() {
        mGoTDataSource = getBestDataSource();

        return GoTCharacterEntityDataMapper.transform(mGoTDataSource.characterEntities());
    }

    private GoTDataSource getBestDataSource() {
        if ((!NetworkGoTDataSource.getInstance().isNetworkAvailable()) ||
                (!CacheGoTPolicy.isCacheExpired()))
            mGoTDataSource = CacheGoTDataSource.getInstance();
        else
            mGoTDataSource = NetworkGoTDataSource.getInstance();

        return mGoTDataSource;
    }
}
