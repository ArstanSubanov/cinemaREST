package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.requests.HallRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface HallService extends BaseService<HallDTO>{
    ResponseEntity<?> create(HallRequest hallRequest);
    ResponseEntity<?> updateHall(int id, HallRequest hallRequest);
}
