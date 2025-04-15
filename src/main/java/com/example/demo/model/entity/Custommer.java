package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "custommer",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cccd"),
                @UniqueConstraint(columnNames = "sodienthoai"),
                @UniqueConstraint(columnNames = "sothenganhang")
        })
public class Custommer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "identityNumber")
    private String identityNumber;//CCCD

    @Column(name = "address")
    private String address;
}
