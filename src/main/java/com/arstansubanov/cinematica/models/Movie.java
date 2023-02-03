package com.arstansubanov.cinematica.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Data
@Table(name = "tb_movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @NotEmpty(message = "name can't be empty")
    @NotBlank(message = "name can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 100 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "description can't be empty")
    @NotBlank(message = "description can't be blank")
    @Size(min = 2, max = 100, message = "description should be between 2 and 5000 characters")
    @Column(name = "description")
    private String description;

    @NotEmpty(message = "age_rating can't be empty")
    @NotBlank(message = "age_rating can't be blank")
    @Size(min = 2, max = 100, message = "age_rating should be between 2 and 10 characters")
    @Column(name = "age_rating")
    private String ageRating;

    @NotEmpty(message = "movie_rating can't be empty")
    @NotBlank(message = "movie_rating can't be blank")
    @Size(min = 2, max = 100, message = "movie_rating should be between 2 and 10 characters")
    @Column(name = "movie_rating")
    private String movieRating;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotEmpty(message = "image can't be empty")
    @NotBlank(message = "image can't be blank")
    @Size(min = 2, max = 100, message = "image should be between 2 and 10 characters")
    @Column(name = "image")
    private String image;

    @AssertFalse(message = "active must be true or false")
    @AssertTrue(message = "active must be true or false")
    @Column(name = "active")
    private boolean active;

    @PrePersist
    private void create(){
        this.createdAt = new Date();
        this.active = true;
    }

    @PreUpdate
    private void update(){
        this.createdAt = new Date();
    }

}

