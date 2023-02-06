package com.arstansubanov.cinematica.responses;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatResponse {
    int id;

    int seatRow;

    int placeNumber;

    String status;

}
