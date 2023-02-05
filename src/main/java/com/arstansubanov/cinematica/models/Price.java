package com.arstansubanov.cinematica.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "tb_price")
public class Price {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "session_id can't be empty")
    @NotBlank(message = "session_id can't be blank")
    @Min(value = 1, message = "session_id must be bigger than 1")
    @NotNull(message = "session_id must be not null")
    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private MovieSession movieSession;

    @NotEmpty(message = "price_type_id can't be empty")
    @NotBlank(message = "price_type_id can't be blank")
    @Min(value = 1, message = "price_type_id must be bigger than 1")
    @NotNull(message = "price_type_id must be not null")
    @ManyToOne
    @JoinColumn(name = "price_type_id", referencedColumnName = "id")
    private PriceType priceType;

    @NotEmpty(message = "price can't be empty")
    @NotBlank(message = "price can't be blank")
    @Min(value = 20, message = "price must be bigger than 20")
    @NotNull(message = "price must be not null")
    @Column(name = "price")
    private int price;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "price")
    private List<Order> orders;

    @PrePersist
    private void addDate(){
        this.createdAt = new Date();
    }


}
