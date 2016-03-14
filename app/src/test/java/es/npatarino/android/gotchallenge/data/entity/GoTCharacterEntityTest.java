package es.npatarino.android.gotchallenge.data.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 * This Test class verifies {@link GoTCharacterEntity} domain model behavior
 */
public class GoTCharacterEntityTest {

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

    private GoTCharacterEntity mCharacterEntity;
    private GoTCharacterEntity.GoTHouseEntity mHouseEntity;

    @Before
    public void setUp() {
        mCharacterEntity = new GoTCharacterEntity();
        mHouseEntity = new GoTCharacterEntity.GoTHouseEntity();
    }

    @Test
    public void testCharacterCreationWithoutName() throws Exception {
        assertNull(mCharacterEntity.getName());
        assertNull(mCharacterEntity.getDescription());
        assertNull(mCharacterEntity.getImageUrl());
        assertNull(mCharacterEntity.getHouseId());
        assertNull(mCharacterEntity.getHouseName());
        assertNull(mCharacterEntity.getHouseUrl());
    }

    @Test
    public void testCharacterCreationWithName() throws Exception {
        mCharacterEntity = new GoTCharacterEntity(NAME);

        assertEquals(NAME, mCharacterEntity.getName());

        assertNull(mCharacterEntity.getDescription());
        assertNull(mCharacterEntity.getImageUrl());
        assertNull(mCharacterEntity.getHouseId());
        assertNull(mCharacterEntity.getHouseName());
        assertNull(mCharacterEntity.getHouseUrl());
    }

    @Test
    public void testName() throws Exception {
        mCharacterEntity.setName(OTHER_NAME);

        String name = mCharacterEntity.getName();

        assertEquals(OTHER_NAME, name);
        assertNotEquals(NAME, name);
    }

    @Test
    public void testDescription() throws Exception {
        mCharacterEntity.setDescription(DESCRIPTION);

        String description = mCharacterEntity.getDescription();

        assertEquals(DESCRIPTION, description);
        assertNotEquals(OTHER_DESCRIPTION, description);
    }

    @Test
    public void testImageUrl() throws Exception {
        mCharacterEntity.setImageUrl(IMAGE_URL);

        String imageUrl = mCharacterEntity.getImageUrl();

        assertEquals(IMAGE_URL, imageUrl);
        assertNotEquals(OTHER_IMAGE_URL, imageUrl);
    }

    @Test
    public void testHouseId() throws Exception {
        mCharacterEntity.setImageUrl(HOUSE_ID);

        String imageUrl = mCharacterEntity.getImageUrl();

        assertEquals(HOUSE_ID, imageUrl);
        assertNotEquals(OTHER_HOUSE_ID, imageUrl);
    }

    @Test
    public void testHouseName() throws Exception {
        mCharacterEntity.setImageUrl(HOUSE_NAME);

        String imageUrl = mCharacterEntity.getImageUrl();

        assertEquals(HOUSE_NAME, imageUrl);
        assertNotEquals(OTHER_HOUSE_NAME, imageUrl);
    }

    @Test
    public void testHouseImageUrl() throws Exception {
        mCharacterEntity.setImageUrl(HOUSE_IMAGE_URL);

        String imageUrl = mCharacterEntity.getImageUrl();

        assertEquals(HOUSE_IMAGE_URL, imageUrl);
        assertNotEquals(OTHER_HOUSE_IMAGE_URL, imageUrl);
    }

    @Test
    public void testHouseCreationWithoutParameters() throws Exception {
        assertNull(mHouseEntity.getId());
        assertNull(mHouseEntity.getName());
        assertNull(mHouseEntity.getImageUrl());
    }

    @Test
    public void testHouseCreationWithParameters() throws Exception {
        mHouseEntity = new GoTCharacterEntity.GoTHouseEntity(HOUSE_ID, HOUSE_NAME, HOUSE_IMAGE_URL);

        assertEquals(HOUSE_ID, mHouseEntity.getId());
        assertEquals(HOUSE_NAME, mHouseEntity.getName());
        assertEquals(HOUSE_IMAGE_URL, mHouseEntity.getImageUrl());
    }
}
