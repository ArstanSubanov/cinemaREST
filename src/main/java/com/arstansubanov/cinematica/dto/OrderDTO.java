package com.arstansubanov.cinematica.dto;

import com.arstansubanov.cinematica.models.Price;
import com.arstansubanov.cinematica.models.Seat;
import com.arstansubanov.cinematica.models.Status;
import com.arstansubanov.cinematica.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "id")
    private int id;

    @NotEmpty(message = "price_id can't be empty")
    @NotBlank(message = "price_id can't be blank")
    @Min(value = 1, message = "price_id must be bigger than 1")
    @NotNull(message = "price_id must be not null")
    private PriceDTO price;

    @NotEmpty(message = "price_id can't be empty")
    @NotBlank(message = "price_id can't be blank")
    @Min(value = 1, message = "price_id must be bigger than 1")
    @NotNull(message = "price_id must be not null")
    private SeatDTO seat;

    private MovieSessionDTO movieSession;

    @NotEmpty(message = "status can't be empty")
    @NotBlank(message = "status can't be blank")
    @Min(value = 1, message = "status must be bigger than 1")
    @NotNull(message = "status must be not null")
    private StatusDTO status;

    @NotEmpty(message = "email can't be empty")
    @NotBlank(message = "email can't be blank")
    @Email(message = "please, enter your email")
    @NotNull(message = "email must be not null")
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "user")
    private UserDTO user;
}
