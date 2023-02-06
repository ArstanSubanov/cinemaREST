package com.arstansubanov.cinematica.requests;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.dto.PriceTypeDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceRequest {

    @NotEmpty(message = "session_id can't be empty")
    @NotBlank(message = "session_id can't be blank")
    @Min(value = 1, message = "session_id must be bigger than 1")
    @NotNull(message = "session_id must be not null")
    private int movieSessionId;

    @NotEmpty(message = "price_type_id can't be empty")
    @NotBlank(message = "price_type_id can't be blank")
    @Min(value = 1, message = "price_type_id must be bigger than 1")
    @NotNull(message = "price_type_id must be not null")
    private int priceTypeId;

    @NotEmpty(message = "price can't be empty")
    @NotBlank(message = "price can't be blank")
    @Min(value = 20, message = "price must be bigger than 20")
    @NotNull(message = "price must be not null")
    private int price;
}
