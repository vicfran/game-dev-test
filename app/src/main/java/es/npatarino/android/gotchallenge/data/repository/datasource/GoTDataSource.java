package es.npatarino.android.gotchallenge.data.repository.datasource;

import java.util.List;

import es.npatarino.android.gotchallenge.data.entity.GoTCharacterEntity;

/**
 * GoTDataSource is an interface representing data sources for getting GoT data
 */
public interface GoTDataSource {

    List<GoTCharacterEntity> characterEntities();
}
