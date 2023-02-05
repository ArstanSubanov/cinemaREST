package com.arstansubanov.cinematica.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Data
@Table(name = "tb_price_type")
public class PriceType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "type_name must be not null ")
    @Size(min = 2, max = 100, message = "name should be between 2 and 200 characters")
    @Column(name = "type_name")
    private String typeName;

    @OneToMany(mappedBy = "priceType")
    private List<Price> prices;
}

