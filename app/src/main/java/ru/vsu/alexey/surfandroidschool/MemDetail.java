package ru.vsu.alexey.surfandroidschool;

public class MemDetail {

    private int id;
    private String description;
    private boolean isFavorite;
    private int createdDate;
    private String photoUrl;

    public MemDetail(){
        this.id = 1231;
        this.description = "Hello moto";
        this.isFavorite = true;
        this.createdDate = 1232151;
        this.photoUrl = "";
    }


    public MemDetail(int id, String description, boolean isFavorite, int createdDate, String photoUrl) {
        this.id = id;
        this.description = description;
        this.isFavorite = isFavorite;
        this.createdDate = createdDate;
        this.photoUrl = photoUrl;
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getCreatedDate() {
        return createdDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
