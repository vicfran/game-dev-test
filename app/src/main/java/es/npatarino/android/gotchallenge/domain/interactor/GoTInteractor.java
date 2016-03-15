package es.npatarino.android.gotchallenge.domain.interactor;

import android.text.TextUtils;

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

    private static List<GoTCharacter.GoTHouse> findHousesFromCharacters(List<GoTCharacter> characters) {
        List<GoTCharacter.GoTHouse> houses = new ArrayList<>();

        if (characters == null)
            return houses;

        for (GoTCharacter character : characters) {
            boolean houseExists = false;
            for (GoTCharacter.GoTHouse house : houses) {
                String characterHouseName = character.getHouseName();
                String houseName = house.getName();

                // Filter houses with empty data
                if ((TextUtils.isEmpty(characterHouseName)) ||
                        (TextUtils.isEmpty(houseName)))
                    continue;
                if (houseName.equalsIgnoreCase(characterHouseName)) {
                    houseExists = true;
                    break;
                }
            }
            if (!houseExists) {
                GoTCharacter.GoTHouse house = new GoTCharacter.GoTHouse();
                house.setId(character.getHouseId());
                house.setName(character.getHouseName());
                house.setImageUrl(character.getHouseUrl());

                // If new house has any empty or null data, avoid adding it to the list
                if (TextUtils.isEmpty(house.getId()) ||
                        TextUtils.isEmpty(house.getName()) ||
                        TextUtils.isEmpty(house.getImageUrl()))
                    continue;

                houses.add(house);
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
