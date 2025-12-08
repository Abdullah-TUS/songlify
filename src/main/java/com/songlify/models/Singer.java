package com.songlify.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "singers")
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String genre;

    @Column(nullable = false)
    private Integer listeners = 0;

    private String country;

    private LocalDate birthdate;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "image_url")
    private String imageUrl;

    public Singer() {
    }

    public Singer(Long id, String name, String genre, Integer listeners, String country, LocalDate birthdate, String bio, String imageUrl) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.listeners = listeners;
        this.country = country;
        this.birthdate = birthdate;
        this.bio = bio;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getListeners() {
        return listeners;
    }

    public void setListeners(Integer listeners) {
        this.listeners = listeners;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
