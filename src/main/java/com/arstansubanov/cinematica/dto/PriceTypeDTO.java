package com.arstansubanov.cinematica.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceTypeDTO {

    @JsonIgnore
    private int id;

    @NotNull(message = "type_name must be not null ")
    @Size(min = 2, max = 100, message = "name should be between 2 and 200 characters")
    private String typeName;

}
