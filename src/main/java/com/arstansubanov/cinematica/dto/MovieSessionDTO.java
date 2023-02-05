package com.arstansubanov.cinematica.dto;

import com.arstansubanov.cinematica.models.Hall;
import com.arstansubanov.cinematica.models.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieSessionDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "id")
    private int id;

    @NotEmpty(message = "seat_rows can't be empty")
    @NotBlank(message = "seat_rows can't be blank")
    @Min(value = 1, message = "sear_rows must be bigger than 1")
    @Max(value = 1000, message = "seat_row must be lower than 1000")
    private MovieDTO movie;

    @NotEmpty(message = "seat_rows can't be empty")
    @NotBlank(message = "seat_rows can't be blank")
    @Min(value = 1, message = "sear_rows must be bigger than 1")
    @Max(value = 1000, message = "seat_row must be lower than 1000")
    private HallDTO hall;

    @NotNull(message = "date can't be null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message = "time can't be null")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime time;
}
