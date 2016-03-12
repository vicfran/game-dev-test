package es.npatarino.android.gotchallenge.data.repository;

import java.util.List;

import es.npatarino.android.gotchallenge.data.entity.mapper.GoTCharacterEntityDataMapper;
import es.npatarino.android.gotchallenge.data.repository.datasource.GoTDataSource;
import es.npatarino.android.gotchallenge.data.repository.datasource.NetworkGoTDataSource;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.repository.GoTRepository;

/**
 * {@GoTRepository} used to retrieve GoT related data
 */
public class GoTDataRepository implements GoTRepository {

    private GoTDataSource mGoTDataSource;
    private GoTCharacterEntityDataMapper mMapper;

    public GoTDataRepository() {
        mGoTDataSource = new NetworkGoTDataSource();
        mMapper = new GoTCharacterEntityDataMapper();
    }

    @Override
    public List<GoTCharacter> characters() {
        return mMapper.transform(mGoTDataSource.characterEntities());
    }
}
