package com.arstansubanov.cinematica.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_session")
public class MovieSession {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "seat_rows can't be empty")
    @NotBlank(message = "seat_rows can't be blank")
    @Min(value = 1, message = "sear_rows must be bigger than 1")
    @Max(value = 1000, message = "seat_row must be lower than 1000")
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @NotEmpty(message = "seat_rows can't be empty")
    @NotBlank(message = "seat_rows can't be blank")
    @Min(value = 1, message = "sear_rows must be bigger than 1")
    @Max(value = 1000, message = "seat_row must be lower than 1000")
    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @NotNull(message = "date can't be null")
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message = "time can't be null")
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime time;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "movieSession")
    private List<Price> prices;

    @PrePersist
    private void addDate(){
        this.createdAt = new Date();
    }


}
