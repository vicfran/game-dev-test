package es.npatarino.android.gotchallenge.domain;

/**
 * GoTCharacter encapsulates a character data model in the domain layer
 */
public class GoTCharacter {

    private String mName;
    private String mDescription;
    private String mImageUrl;
    private String mHouseId;
    private String mHouseName;
    private String mHouseUrl;

    public GoTCharacter() {}

    public GoTCharacter(String name) {
        mName = name;
    }

    public String getHouseUrl() {
        return mHouseUrl;
    }

    public void setHouseUrl(final String houseUrl) {
        mHouseUrl = houseUrl;
    }

    public String getHouseName() {
        return mHouseName;
    }

    public void setHouseName(final String houseName) {
        mHouseName = houseName;
    }

    public String getHouseId() {
        return mHouseId;
    }

    public void setHouseId(final String houseId) {
        mHouseId = houseId;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(final String description) {
        mDescription = description;
    }

    public static class GoTHouse {

        private String mId;
        private String mName;
        private String mImageUrl;

        public GoTHouse() {}

        public GoTHouse(String id, String name,
                        String imageUrl) {
            mId = id;
            mName = name;
            mImageUrl = imageUrl;
        }

        public String getId() {
            return mId;
        }

        public void setId(final String id) {
            mId = id;
        }

        public String getName() {
            return mName;
        }

        public void setName(final String name) {
            mName = name;
        }

        public String getImageUrl() {
            return mImageUrl;
        }

        public void setImageUrl(final String imageUrl) {
            mImageUrl = imageUrl;
        }
    }
}
