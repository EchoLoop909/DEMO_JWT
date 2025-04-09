package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "Username"),
                @UniqueConstraint(columnNames = "Email")
        })
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @NotBlank
    @Column(name = "Username")
    private String username;

    @NotBlank
    @Size(min = 6, max = 50)
    @Email
    @Column(name = "Email")
    private String email;

    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 120)
    @Column(name = "Password")
    private String password;

}
