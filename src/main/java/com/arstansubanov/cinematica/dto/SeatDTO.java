package com.arstansubanov.cinematica.dto;

import com.arstansubanov.cinematica.models.Hall;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "id")
    private int id;

    private int seatRow;

    private int placeNumber;

    private Hall hall;
}
