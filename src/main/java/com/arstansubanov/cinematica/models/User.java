package com.arstansubanov.cinematica.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "tb_users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "username can't be empty")
    @NotBlank(message = "username can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 50 characters")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "password can't be empty")
    @NotBlank(message = "password can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 100 characters")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "email can't be empty")
    @NotBlank(message = "email can't be blank")
    @Size(min = 2, max = 100, message = "name should be between 2 and 50 characters")
    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @PrePersist
    private void addDate(){
        this.createdAt = new Date();
    }
}
