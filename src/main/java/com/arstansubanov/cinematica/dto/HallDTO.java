package com.arstansubanov.cinematica.dto;

import com.arstansubanov.cinematica.models.Cinema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallDTO {

    @JsonIgnore
    private int id;

    @NotEmpty(message = "name can't be empty")
    @NotBlank(message = "name can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 200 characters")
    private String name;

    @NotEmpty(message = "seat_rows can't be empty")
    @NotBlank(message = "seat_rows can't be blank")
    @Min(value = 1, message = "sear_rows must be bigger than 1")
    @Max(value = 1000, message = "seat_row must be lower than 1000")
    private int seatRows;

    @NotEmpty(message = "place_numbers can't be empty")
    @NotBlank(message = "place_numbers can't be blank")
    @Min(value = 1, message = "place_numbers must be bigger than 1")
    @Max(value = 1000, message = "place_numbers must be lower than 1000")
    private int placeNumbers;

    private Cinema cinema;

}
