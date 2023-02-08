package com.arstansubanov.cinematica.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {

    int sessionId;

    @NotEmpty(message = "price_id can't be empty")
    @NotBlank(message = "price_id can't be blank")
    @Min(value = 1, message = "price_id must be bigger than 1")
    @NotNull(message = "price_id must be not null")
    int priceId;

    @NotEmpty(message = "price_id can't be empty")
    @NotBlank(message = "price_id can't be blank")
    @Min(value = 1, message = "price_id must be bigger than 1")
    @NotNull(message = "price_id must be not null")
    int seatId;

    @NotEmpty(message = "status can't be empty")
    @NotBlank(message = "status can't be blank")
    @Min(value = 1, message = "status must be bigger than 1")
    @NotNull(message = "status must be not null")
    int statusId;

    @NotEmpty(message = "email can't be empty")
    @NotBlank(message = "email can't be blank")
    @Email(message = "please, enter your email")
    @NotNull(message = "email must be not null")
    String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int userId;
}
