package com.arstansubanov.cinematica.mapper;

public interface BaseMapper<M, D> {
    M convertToModel(D d);
    D convertToDto(M m);
}
