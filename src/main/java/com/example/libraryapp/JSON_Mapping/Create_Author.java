package com.example.libraryapp.JSON_Mapping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Create_Author {
    @JsonProperty("publisher_name")
    private String publisher_name;
    @JsonProperty("authorFirstName")
    private String authorFirstName;
    @JsonProperty("authorLastName")
    private String authorLastName;
    @JsonProperty("biography")
    private String biography;

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
