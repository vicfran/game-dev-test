package es.npatarino.android.gotchallenge.domain.interactor;

import java.util.ArrayList;
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
     * Use case for retrieving group of {@link GoTCharacter}
     * @return A {@link List} of {@link GoTCharacter}
     */
    public static List<GoTCharacter> getCharacters() {
        return sGoTRepository.characters();
    }

    /**
     * Use case for retrieving group of {@link GoTCharacter.GoTHouse}
     * @return A {@link List} of {@link GoTCharacter.GoTHouse}
     */
    public static List<GoTCharacter.GoTHouse> getHouses() {
        return findHousesFromCharacters(sGoTRepository.characters());
    }

    /**
     * Use case for retrieving group of {@link GoTCharacter} belonging to one {@link GoTCharacter.GoTHouse}
     * @param house {@link GoTCharacter.GoTHouse} to search characters
     * @return {@link List} of {@link GoTCharacter} that belong to the {@link GoTCharacter.GoTHouse}
     */
    public static List<GoTCharacter> getCharactersOfHouse(GoTCharacter.GoTHouse house) {
        return findCharactersFromHouse(house);
    }

    public static List<GoTCharacter> getCharactersByName(String name) {
        return findCharactersByName(name);
    }

    // TODO : check about move this logic to repository itself
    private static List<GoTCharacter.GoTHouse> findHousesFromCharacters(List<GoTCharacter> characters) {
        List<GoTCharacter.GoTHouse> houses = new ArrayList<>();

        if (characters == null)
            return houses;

        for (int i = 0; i < characters.size(); i++) {
            boolean houseExists = false;
            for (int j = 0; j < houses.size(); j++) {
                if (houses.get(j).getName().equalsIgnoreCase(characters.get(i).getName())) {
                    houseExists = true;
                }
            }
            if (!houseExists) {
                if (characters.get(i).getImageUrl() != null && !characters.get(i).getImageUrl().isEmpty()) {
                    GoTCharacter.GoTHouse house = new GoTCharacter.GoTHouse();
                    house.setId(characters.get(i).getHouseId());
                    house.setName(characters.get(i).getHouseName());
                    house.setImageUrl(characters.get(i).getHouseUrl());
                    houses.add(house);
                    houseExists = false;
                }
            }
        }

        return houses;
    }

    private static List<GoTCharacter> findCharactersFromHouse(GoTCharacter.GoTHouse house) {
        List<GoTCharacter> characters = new ArrayList<>();

        if (house == null)
            return characters;

        List<GoTCharacter> data = getCharacters();
        for (GoTCharacter character : data) {
            String id = character.getHouseId();

            if ((id == null) || (id.isEmpty()))
                continue;

            if (id.equals(house.getId()))
                characters.add(character);
        }

        return characters;
    }

    private static List<GoTCharacter> findCharactersByName(String name) {
        List<GoTCharacter> characters = new ArrayList<>();

        if ((name == null) || (name.isEmpty()))
            return characters;

        // Ignore queries too short
        if (name.length() < 3)
            return characters;

        List<GoTCharacter> data = getCharacters();
        for (GoTCharacter character : data) {
            if (character.getName().toLowerCase().contains(name.toLowerCase()))
                characters.add(character);
        }

        return characters;
    }
}
