package com.example.libraryapp.JSON_Mapping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Create_Books {
    @JsonProperty("ISBN")
    private long ISBN;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private double price;
    @JsonProperty("genre")
    private String genre;
    @JsonProperty("year_published")
    private int year_published;
    @JsonProperty("copies_sold")
    private int copies_sold;
    @JsonProperty("publisher_name")
    private String publisher_name;
    @JsonProperty("authorFirstName")
    private String authorFirstName;
    @JsonProperty("authorLastName")
    private String authorLastName;
    @JsonProperty("biography")
    private String biography;

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    public int getCopies_sold() {
        return copies_sold;
    }

    public void setCopies_sold(int copies_sold) {
        this.copies_sold = copies_sold;
    }

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
}
