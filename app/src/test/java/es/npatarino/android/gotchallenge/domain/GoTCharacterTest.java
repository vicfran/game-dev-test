package es.npatarino.android.gotchallenge.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 * This Test class verifies {@link GoTCharacter} domain model behavior
 */
public class GoTCharacterTest {

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

    private GoTCharacter mCharacter;
    private GoTCharacter.GoTHouse mHouse;

    @Before
    public void setUp() {
        mCharacter = new GoTCharacter();
        mHouse = new GoTCharacter.GoTHouse();
    }

    @Test
    public void testCharacterCreationWithoutName() throws Exception {
        assertNull(mCharacter.getName());
        assertNull(mCharacter.getDescription());
        assertNull(mCharacter.getImageUrl());
        assertNull(mCharacter.getHouseId());
        assertNull(mCharacter.getHouseName());
        assertNull(mCharacter.getHouseUrl());
    }

    @Test
    public void testCharacterCreationWithName() throws Exception {
        mCharacter = new GoTCharacter(NAME);

        assertEquals(NAME, mCharacter.getName());

        assertNull(mCharacter.getDescription());
        assertNull(mCharacter.getImageUrl());
        assertNull(mCharacter.getHouseId());
        assertNull(mCharacter.getHouseName());
        assertNull(mCharacter.getHouseUrl());
    }

    @Test
    public void testName() throws Exception {
        mCharacter.setName(OTHER_NAME);

        String name = mCharacter.getName();

        assertEquals(OTHER_NAME, name);
        assertNotEquals(NAME, name);
    }

    @Test
    public void testDescription() throws Exception {
        mCharacter.setDescription(DESCRIPTION);

        String description = mCharacter.getDescription();

        assertEquals(DESCRIPTION, description);
        assertNotEquals(OTHER_DESCRIPTION, description);
    }

    @Test
    public void testImageUrl() throws Exception {
        mCharacter.setImageUrl(IMAGE_URL);

        String imageUrl = mCharacter.getImageUrl();

        assertEquals(IMAGE_URL, imageUrl);
        assertNotEquals(OTHER_IMAGE_URL, imageUrl);
    }

    @Test
    public void testHouseId() throws Exception {
        mCharacter.setImageUrl(HOUSE_ID);

        String imageUrl = mCharacter.getImageUrl();

        assertEquals(HOUSE_ID, imageUrl);
        assertNotEquals(OTHER_HOUSE_ID, imageUrl);
    }

    @Test
    public void testHouseName() throws Exception {
        mCharacter.setImageUrl(HOUSE_NAME);

        String imageUrl = mCharacter.getImageUrl();

        assertEquals(HOUSE_NAME, imageUrl);
        assertNotEquals(OTHER_HOUSE_NAME, imageUrl);
    }

    @Test
    public void testHouseImageUrl() throws Exception {
        mCharacter.setImageUrl(HOUSE_IMAGE_URL);

        String imageUrl = mCharacter.getImageUrl();

        assertEquals(HOUSE_IMAGE_URL, imageUrl);
        assertNotEquals(OTHER_HOUSE_IMAGE_URL, imageUrl);
    }

    @Test
    public void testHouseCreationWithoutParameters() throws Exception {
        assertNull(mHouse.getId());
        assertNull(mHouse.getName());
        assertNull(mHouse.getImageUrl());
    }

    @Test
    public void testHouseCreationWithParameters() throws Exception {
        mHouse = new GoTCharacter.GoTHouse(HOUSE_ID, HOUSE_NAME, HOUSE_IMAGE_URL);

        assertEquals(HOUSE_ID, mHouse.getId());
        assertEquals(HOUSE_NAME, mHouse.getName());
        assertEquals(HOUSE_IMAGE_URL, mHouse.getImageUrl());
    }
}