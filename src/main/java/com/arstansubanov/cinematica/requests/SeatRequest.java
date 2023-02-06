package com.arstansubanov.cinematica.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatRequest {
    private int id;

    private int seatRow;

    private int placeNumber;

    private int hallId;
}
