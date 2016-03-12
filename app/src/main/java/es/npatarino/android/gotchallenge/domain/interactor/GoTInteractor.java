package es.npatarino.android.gotchallenge.domain.interactor;

import java.util.List;

import es.npatarino.android.gotchallenge.data.repository.GoTDataRepository;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.repository.GoTRepository;

/**
 * Interactor used to implement the use cases of the application
 * following the bussiness rules.
 */
public final class GoTInteractor {

    private static final GoTRepository sGoTRepository = new GoTDataRepository();

    private GoTInteractor() {}

    /**
     * Use case for retrieving GoT characters
     * @return A {@link List} of {@link GoTCharacter}
     */
    public static List<GoTCharacter> getCharacters() {
        return sGoTRepository.characters();
    }
}
