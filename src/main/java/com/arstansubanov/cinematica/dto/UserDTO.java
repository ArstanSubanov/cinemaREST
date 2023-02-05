package com.arstansubanov.cinematica.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "id")
    private int id;

    @NotEmpty(message = "username can't be empty")
    @NotBlank(message = "username can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 50 characters")
    private String username;

    @NotEmpty(message = "password can't be empty")
    @NotBlank(message = "password can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 100 characters")
    private String password;

    @NotEmpty(message = "email can't be empty")
    @NotBlank(message = "email can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 50 characters")
    @Email(message = "Please, enter your email")
    private String email;

}
