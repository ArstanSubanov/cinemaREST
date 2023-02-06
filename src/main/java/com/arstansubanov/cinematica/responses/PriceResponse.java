package com.arstansubanov.cinematica.responses;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceResponse {
    int id;
    String priceType;
    int price;
}
