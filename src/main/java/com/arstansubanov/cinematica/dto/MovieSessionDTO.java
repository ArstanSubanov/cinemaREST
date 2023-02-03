package com.arstansubanov.cinematica.dto;

import com.arstansubanov.cinematica.models.Hall;
import com.arstansubanov.cinematica.models.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalTime;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieSessionDTO {

    @JsonIgnore
    private int id;

    @NotEmpty(message = "seat_rows can't be empty")
    @NotBlank(message = "seat_rows can't be blank")
    @Min(value = 1, message = "sear_rows must be bigger than 1")
    @Max(value = 1000, message = "seat_row must be lower than 1000")
    private Movie movie;

    @NotEmpty(message = "seat_rows can't be empty")
    @NotBlank(message = "seat_rows can't be blank")
    @Min(value = 1, message = "sear_rows must be bigger than 1")
    @Max(value = 1000, message = "seat_row must be lower than 1000")
    private Hall hall;

    @NotNull(message = "date can't be null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message = "time can't be null")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime time;
}
