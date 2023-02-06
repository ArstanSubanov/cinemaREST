package com.arstansubanov.cinematica.responses;

import com.arstansubanov.cinematica.dto.CinemaDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaResponse {
    String name;
    List<HallResponse> halls;
}
