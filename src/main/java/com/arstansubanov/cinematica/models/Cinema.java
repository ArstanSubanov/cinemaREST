package com.arstansubanov.cinematica.models;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "tb_cinema")
public class Cinema {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "name can't be empty")
    @NotBlank(message = "name can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 200 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "adress can't be empty")
    @NotBlank(message = "adress can't be blank")
    @Size(min = 2, max = 100, message = "adress should be between 2 and 200 characters")
    @Column(name = "adress")
    private String adress;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    private void addDate(){
        this.createdAt = new Date();
    }

}
