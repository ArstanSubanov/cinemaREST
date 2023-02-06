package com.arstansubanov.cinematica.responses;

import com.arstansubanov.cinematica.dto.MovieDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    MovieDTO movie;
    List<CinemaResponse> cinemas;
}
