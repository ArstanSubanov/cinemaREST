package com.arstansubanov.cinematica.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "tb_hall")
public class Hall {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "name can't be empty")
    @NotBlank(message = "name can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 200 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "seat_rows can't be empty")
    @NotBlank(message = "seat_rows can't be blank")
    @Min(value = 1, message = "sear_rows must be bigger than 1")
    @Max(value = 1000, message = "seat_row must be lower than 1000")
    @Column(name = "seat_rows")
    private int seatRows;

    @NotEmpty(message = "place_numbers can't be empty")
    @NotBlank(message = "place_numbers can't be blank")
    @Min(value = 1, message = "place_numbers must be bigger than 1")
    @Max(value = 1000, message = "place_numbers must be lower than 1000")
    @Column(name = "place_numbers")
    private int placeNumbers;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    private Cinema cinema;

    @OneToMany(mappedBy = "hall")
    private List<Seat> seats;

    @OneToMany(mappedBy = "hall")
    private List<MovieSession> movieSessions;

    @PrePersist
    @PreUpdate
    public void addDate(){
        this.createdAt = new Date();
    }


}
