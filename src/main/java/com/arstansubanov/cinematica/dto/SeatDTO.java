package com.arstansubanov.cinematica.dto;

import com.arstansubanov.cinematica.models.Hall;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatDTO {

    @JsonIgnore
    private int id;

    private int seatRow;

    private int placeNumber;

    private Hall hall;
}
