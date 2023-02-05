package com.arstansubanov.cinematica.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Data
@Table(name = "tb_status")
public class Status {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "type_name must be not null ")
    @Size(min = 2, max = 100, message = "name should be between 2 and 50 characters")
    @Column(name = "status_type")
    private String statusType;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;
}
