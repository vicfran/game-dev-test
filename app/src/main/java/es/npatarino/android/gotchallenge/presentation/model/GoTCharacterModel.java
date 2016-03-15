package es.npatarino.android.gotchallenge.presentation.model;

import com.google.gson.annotations.SerializedName;

/**
 * GoTCharacterModel encapsulates a character data model in the presentation layer
 */
public class GoTCharacterModel {

    private String name;
    private String imageUrl;
    private String description;
    private String houseUrl;
    private String houseName;
    private String houseId;

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


    public static class GoTHouseModel {

        private String imageUrl;
        private String name;
        private String id;

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
