package com.arstansubanov.cinematica.responses;

import com.arstansubanov.cinematica.dto.HallDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallResponse {
    String name;
    List<MovieSessionResponse> movieSessions;
}
