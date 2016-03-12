package es.npatarino.android.gotchallenge.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.repository.GoTRepository;

/**
 * {@GoTRepository} used to retrieve GoT related data
 */
public class GoTDataRepository implements GoTRepository {

    @Override
    public List<GoTCharacter> caracters() {
        List<GoTCharacter> characters = new ArrayList<>();

        // TODO : retrieve characters

        return characters;
    }
}
