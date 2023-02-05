package com.arstansubanov.cinematica.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "id")
    private int id;

    @NotEmpty(message = "name can't be empty")
    @NotBlank(message = "name can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 200 characters")
    private String name;

    @NotEmpty(message = "adress can't be empty")
    @NotBlank(message = "adress can't be blank")
    @Size(min = 2, max = 100, message = "adress should be between 2 and 200 characters")
    private String adress;
}
