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

    public static GoTCharacterModel transform(GoTCharacter character) {
        GoTCharacterModel model = new GoTCharacterModel();

        if (character == null)
            return model;

        // TODO : make the transformation

        return model;
    }

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
