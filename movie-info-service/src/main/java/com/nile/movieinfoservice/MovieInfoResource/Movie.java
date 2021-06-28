package com.nile.movieinfoservice.MovieInfoResource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    private int id;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("overview")
    private String desciption;

    public Movie() {
    }

    public Movie(int id, String originalTitle, String desciption) {
        this.id = id;
        this.originalTitle = originalTitle;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
}
