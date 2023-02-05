package com.arstansubanov.cinematica.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallRequest {
    String name;
    int seatRows;
    int placeNumbers;
    int cinemaId;

}
