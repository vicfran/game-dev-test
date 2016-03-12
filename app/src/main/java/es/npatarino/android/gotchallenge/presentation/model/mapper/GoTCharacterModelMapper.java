package es.npatarino.android.gotchallenge.presentation.model.mapper;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;

/**
 * Transforms {@link GoTCharacter} from domain layer to {@link GoTCharacterModel} of presentation layer
 */
public final class GoTCharacterModelMapper {

    private GoTCharacterModelMapper() {}

    /**
     * Transforms a {@link GoTCharacter} into a {@link GoTCharacterModel}
     * @param character {@link GoTCharacter} to transform
     * @return {@link GoTCharacterModel} obtained from a {@link GoTCharacter}
     */
    public static GoTCharacterModel transform(GoTCharacter character) {
        GoTCharacterModel model = new GoTCharacterModel();

        if (character == null)
            return model;

        model.setName(character.getName());
        model.setDescription(character.getDescription());
        model.setImageUrl(character.getImageUrl());
        model.setHouseUrl(character.getHouseUrl());
        model.setHouseName(character.getHouseName());
        model.setHouseId(character.getHouseId());

        return model;
    }

    /**
     * Transforms a {@link List} of {@link GoTCharacter} into a {@link List} of {@link GoTCharacterModel}
     * @param characters {@link List} of {@link GoTCharacter} to transform
     * @return {@link List} of {@link GoTCharacterModel} obtained from a {@link List} of {@link GoTCharacter}
     */
    public static List<GoTCharacterModel> transform(List<GoTCharacter> characters) {
        List<GoTCharacterModel> models = new ArrayList<>();

        if (characters == null)
            return models;

        for (GoTCharacter character : characters) {
            GoTCharacterModel model = transform(character);
            if (model != null)
                models.add(model);
        }

        return models;
    }
}
