package es.npatarino.android.gotchallenge.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.data.entity.GoTCharacterEntity;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;

/**
 * Transforms {@link GoTCharacterEntity} from data layer to {@link GoTCharacter} of domain layer
 */
public final class GoTCharacterEntityDataMapper {

    private GoTCharacterEntityDataMapper() {}

    /**
     * Transforms a {@link GoTCharacterEntity} into a {@link GoTCharacter}
     * @param entity {@link GoTCharacterEntity} to transform
     * @return {@link GoTCharacter} obtained from a {@link GoTCharacterEntity}
     */
    public static GoTCharacter transform(GoTCharacterEntity entity) {
        GoTCharacter model = new GoTCharacter();

        if (entity == null)
            return model;

        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setImageUrl(entity.getImageUrl());
        model.setHouseUrl(entity.getHouseUrl());
        model.setHouseName(entity.getHouseName());
        model.setHouseId(entity.getHouseId());

        return model;
    }

    /**
     * Transforms a {@link List} of {@link GoTCharacterEntity} into a {@link List} of {@link GoTCharacter}
     * @param entities {@link List} of {@link GoTCharacterEntity} to transform
     * @return {@link List} of {@link GoTCharacter} obtained from a {@link List} of {@link GoTCharacterEntity}
     */
    public static List<GoTCharacter> transform(List<GoTCharacterEntity> entities) {
        List<GoTCharacter> models = new ArrayList<>();

        if (entities == null)
            return models;

        for (GoTCharacterEntity entity : entities) {
            GoTCharacter model = transform(entity);
            if (model != null)
                models.add(model);
        }

        return models;
    }
}
