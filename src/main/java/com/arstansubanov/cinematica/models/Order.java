package com.arstansubanov.cinematica.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tb_order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "price_id can't be empty")
    @NotBlank(message = "price_id can't be blank")
    @Min(value = 1, message = "price_id must be bigger than 1")
    @NotNull(message = "price_id must be not null")
    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Price price;

    @NotEmpty(message = "price_id can't be empty")
    @NotBlank(message = "price_id can't be blank")
    @Min(value = 1, message = "price_id must be bigger than 1")
    @NotNull(message = "price_id must be not null")
    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;

    @NotEmpty(message = "status can't be empty")
    @NotBlank(message = "status can't be blank")
    @Min(value = 1, message = "status must be bigger than 1")
    @NotNull(message = "status must be not null")
    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    private Status status;

    @NotEmpty(message = "email can't be empty")
    @NotBlank(message = "email can't be blank")
    @Email(message = "please, enter your email")
    @NotNull(message = "email must be not null")
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    private void addDate(){
        this.createdAt = new Date();
    }



}

