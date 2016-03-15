package es.npatarino.android.gotchallenge.presentation.model.mapper;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;

/**
 * Transforms {@link GoTCharacter.GoTHouse} from domain layer to {@link GoTCharacterModel.GoTHouseModel} of presentation layer
 */
public final class GoTHouseModelMapper {

    private GoTHouseModelMapper() {}

    /**
     * Transforms a {@link GoTCharacter.GoTHouse} into a {@link GoTCharacterModel.GoTHouseModel}
     * @param house {@link GoTCharacter.GoTHouse} to transform
     * @return {@link GoTCharacterModel.GoTHouseModel} obtained from a {@link GoTCharacter.GoTHouse}
     */
    public static GoTCharacterModel.GoTHouseModel transform(GoTCharacter.GoTHouse house) {
        GoTCharacterModel.GoTHouseModel model = new GoTCharacterModel.GoTHouseModel();

        if (house == null)
            return model;

        model.setId(house.getId());
        model.setName(house.getName());
        model.setImageUrl(house.getImageUrl());


        return model;
    }

    /**
     * Transforms a {@link List} of {@link GoTCharacter.GoTHouse} into a {@link List} of {@link GoTCharacterModel.GoTHouseModel}
     * @param houses {@link List} of {@link GoTCharacter.GoTHouse} to transform
     * @return {@link List} of {@link GoTCharacterModel.GoTHouseModel} obtained from a {@link List} of {@link GoTCharacter.GoTHouse}
     */
    public static List<GoTCharacterModel.GoTHouseModel> transform(List<GoTCharacter.GoTHouse> houses) {
        List<GoTCharacterModel.GoTHouseModel> models = new ArrayList<>();

        if (houses == null)
            return models;

        for (GoTCharacter.GoTHouse house : houses) {
            GoTCharacterModel.GoTHouseModel model = transform(house);
            if (model != null)
                models.add(model);
        }

        return models;
    }
}
