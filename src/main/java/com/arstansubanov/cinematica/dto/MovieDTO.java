package com.arstansubanov.cinematica.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDTO {

    @JsonIgnore
    private  int id;

    @NotEmpty(message = "name can't be empty")
    @NotBlank(message = "name can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "description can't be empty")
    @NotBlank(message = "description can't be blank")
    @Size(min = 2, max = 100, message = "description should be between 2 and 5000 characters")
    private String description;

    @NotEmpty(message = "age_rating can't be empty")
    @NotBlank(message = "age_rating can't be blank")
    @Size(min = 2, max = 100, message = "age_rating should be between 2 and 10 characters")
    private String ageRating;

    @NotEmpty(message = "movie_rating can't be empty")
    @NotBlank(message = "movie_rating can't be blank")
    @Size(min = 2, max = 100, message = "movie_rating should be between 2 and 10 characters")
    private String movieRating;

    @NotEmpty(message = "image can't be empty")
    @NotBlank(message = "image can't be blank")
    @Size(min = 2, max = 100, message = "image should be between 2 and 10 characters")
    private String image;

    @AssertFalse(message = "active must be true or false")
    @AssertTrue(message = "active must be true or false")
    private boolean active;
}
