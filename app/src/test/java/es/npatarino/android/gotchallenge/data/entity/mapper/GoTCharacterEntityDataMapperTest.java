package es.npatarino.android.gotchallenge.data.entity.mapper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.data.entity.GoTCharacterEntity;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This Test class verifies {@link GoTCharacterEntityDataMapper} domain model behavior
 */
public class GoTCharacterEntityDataMapperTest {

    private static final String NAME = "name";
    private static final String OTHER_NAME = "other_name";

    private static final String DESCRIPTION = "description";
    private static final String OTHER_DESCRIPTION = "other_description";

    private static final String IMAGE_URL = "image_url";
    private static final String OTHER_IMAGE_URL = "other_image_url";

    private static final String HOUSE_ID = "house_id";
    private static final String OTHER_HOUSE_ID = "other_house_id";

    private static final String HOUSE_NAME = "house_name";
    private static final String OTHER_HOUSE_NAME = "other_house_name";

    private static final String HOUSE_IMAGE_URL = "house_image_url";
    private static final String OTHER_HOUSE_IMAGE_URL = "other_house_image_url";

    private GoTCharacterEntity mGoTCharacterEntity;
    private List<GoTCharacterEntity> mGoTCharacterEntities;

    @Before
    public void setUp() {
        mGoTCharacterEntity = buildGoTCharacterEntity();
        mGoTCharacterEntities = buildGoTCharacterEntities();
    }

    @Test
    public void testTransformGoTCharacterEntitySuccesful() throws Exception {
        GoTCharacter goTCharacter = GoTCharacterEntityDataMapper.transform(mGoTCharacterEntity);

        assertEquals(NAME, goTCharacter.getName());
        assertEquals(DESCRIPTION, goTCharacter.getDescription());
        assertEquals(IMAGE_URL, goTCharacter.getImageUrl());
        assertEquals(HOUSE_ID, goTCharacter.getHouseId());
        assertEquals(HOUSE_NAME, goTCharacter.getHouseName());
        assertEquals(HOUSE_IMAGE_URL, goTCharacter.getHouseUrl());
    }

    @Test
    public void testTransformGoTCharacterEntityUnsuccesful() throws Exception {
        GoTCharacter goTCharacter = GoTCharacterEntityDataMapper.transform(mGoTCharacterEntity);
        GoTCharacter incorrectGoTCharacter = buildIncorrectGoTCharacter();

        assertNotEquals(incorrectGoTCharacter.getName(), goTCharacter.getName());
        assertNotEquals(incorrectGoTCharacter.getDescription(), goTCharacter.getDescription());
        assertNotEquals(incorrectGoTCharacter.getImageUrl(), goTCharacter.getImageUrl());
        assertNotEquals(incorrectGoTCharacter.getHouseId(), goTCharacter.getHouseId());
        assertNotEquals(incorrectGoTCharacter.getHouseName(), goTCharacter.getHouseName());
        assertNotEquals(incorrectGoTCharacter.getHouseUrl(), goTCharacter.getHouseUrl());
    }

    @Test
    public void testTransformGoTCharacterEntitiesSuccesful() throws Exception {
        List<GoTCharacter> goTCharacters = GoTCharacterEntityDataMapper.transform(mGoTCharacterEntities);

        for (GoTCharacter goTCharacter : goTCharacters) {
            assertEquals(NAME, goTCharacter.getName());
            assertEquals(DESCRIPTION, goTCharacter.getDescription());
            assertEquals(IMAGE_URL, goTCharacter.getImageUrl());
            assertEquals(HOUSE_ID, goTCharacter.getHouseId());
            assertEquals(HOUSE_NAME, goTCharacter.getHouseName());
            assertEquals(HOUSE_IMAGE_URL, goTCharacter.getHouseUrl());
        }
    }

    @Test
    public void testTransformGoTCharacterEntitiesUnsuccesful() throws Exception {
        List<GoTCharacter> goTCharacters = GoTCharacterEntityDataMapper.transform(mGoTCharacterEntities);
        List<GoTCharacter> incorrectGoTCharacters = buildIncorrectGoTCharacters();

        for (int i = 0; i < 5; i++) {
            GoTCharacter goTCharacter = goTCharacters.get(i);
            GoTCharacter incorrectGoTCharacter = incorrectGoTCharacters.get(i);

            assertNotEquals(incorrectGoTCharacter.getName(), goTCharacter.getName());
            assertNotEquals(incorrectGoTCharacter.getDescription(), goTCharacter.getDescription());
            assertNotEquals(incorrectGoTCharacter.getImageUrl(), goTCharacter.getImageUrl());
            assertNotEquals(incorrectGoTCharacter.getHouseId(), goTCharacter.getHouseId());
            assertNotEquals(incorrectGoTCharacter.getHouseName(), goTCharacter.getHouseName());
            assertNotEquals(incorrectGoTCharacter.getHouseUrl(), goTCharacter.getHouseUrl());
        }
    }

    private GoTCharacterEntity buildGoTCharacterEntity() {
        mGoTCharacterEntity = new GoTCharacterEntity();

        mGoTCharacterEntity.setName(NAME);
        mGoTCharacterEntity.setDescription(DESCRIPTION);
        mGoTCharacterEntity.setImageUrl(IMAGE_URL);
        mGoTCharacterEntity.setHouseId(HOUSE_ID);
        mGoTCharacterEntity.setHouseName(HOUSE_NAME);
        mGoTCharacterEntity.setHouseUrl(HOUSE_IMAGE_URL);

        return mGoTCharacterEntity;
    }

    private GoTCharacter buildIncorrectGoTCharacter() {
        GoTCharacter mGoTCharacter = new GoTCharacter();

        mGoTCharacter.setName(OTHER_NAME);
        mGoTCharacter.setDescription(OTHER_DESCRIPTION);
        mGoTCharacter.setImageUrl(OTHER_IMAGE_URL);
        mGoTCharacter.setHouseId(OTHER_HOUSE_ID);
        mGoTCharacter.setHouseName(OTHER_HOUSE_NAME);
        mGoTCharacter.setHouseUrl(OTHER_HOUSE_IMAGE_URL);

        return mGoTCharacter;
    }

    private List<GoTCharacterEntity> buildGoTCharacterEntities() {
        List<GoTCharacterEntity> characters = new ArrayList<>();

        for (int i = 0; i < 5; i++)
            characters.add(buildGoTCharacterEntity());

        return characters;
    }

    private List<GoTCharacter> buildIncorrectGoTCharacters() {
        List<GoTCharacter> characters = new ArrayList<>();

        for (int i = 0; i < 5; i++)
            characters.add(buildIncorrectGoTCharacter());

        return characters;
    }
}
