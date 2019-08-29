package ru.vsu.alexey.surfandroidschool.model;

import com.google.gson.annotations.SerializedName;

public class MemResponse {
    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("isFavorite")
    private boolean isFavorite;

    @SerializedName("createdDate")
    private long createdDate;

    @SerializedName("photoUrl")
    private String photoUrl;


    public MemResponse(long id, String title, String description, boolean isFavorite, long createdDate, String photoUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isFavorite = isFavorite;
        this.createdDate = createdDate;
        this.photoUrl = photoUrl;
    }
    public MemResponse(){
        this.id = 1231;
        this.description = "MEMES";
        this.isFavorite = true;
        this.createdDate = 1232151;
        this.photoUrl = "";
    }

    public long getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public String getPhotoUrl() {return photoUrl; }


}

