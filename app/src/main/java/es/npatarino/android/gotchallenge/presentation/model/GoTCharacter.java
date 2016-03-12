package es.npatarino.android.gotchallenge.presentation.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolás Patarino on 21/02/16.
 */
public class GoTCharacter {

    @SerializedName("name")
    public String name;
    @SerializedName("imageUrl")
    public String imageUrl;
    @SerializedName("description")
    public String description;
    @SerializedName("houseImageUrl")
    public String houseUrl;
    @SerializedName("houseName")
    public String houseName;
    @SerializedName("houseId")
    public String houseId;

    public String getHouseUrl() {
        return houseUrl;
    }

    public void setHouseUrl(final String houseUrl) {
        this.houseUrl = houseUrl;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(final String houseName) {
        this.houseName = houseName;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(final String houseId) {
        this.houseId = houseId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Created by Nicolás Patarino on 21/02/16.
     */
    public static class GoTHouse {

        @SerializedName("houseImageUrl")
        public String imageUrl;
        @SerializedName("houseName")
        public String name;
        @SerializedName("houseId")
        public String id;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(final String id) {
            this.id = id;
        }

    }
}
