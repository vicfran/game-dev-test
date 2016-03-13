package es.npatarino.android.gotchallenge.data.entity;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * GoTCharacterEntity encapsulates a character data model in the data layer
 */
public class GoTCharacterEntity extends SugarRecord {

    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("houseId")
    private String mHouseId;
    @SerializedName("houseName")
    private String mHouseName;
    @SerializedName("houseImageUrl")
    private String mHouseUrl;

    public GoTCharacterEntity() {}

    public GoTCharacterEntity(String name) {
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

    public static class GoTHouseEntity {

        @SerializedName("houseId")
        private String mId;
        @SerializedName("houseName")
        private String mName;
        @SerializedName("houseImageUrl")
        private String mImageUrl;

        public GoTHouseEntity() {}

        public GoTHouseEntity(String id, String name,
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
