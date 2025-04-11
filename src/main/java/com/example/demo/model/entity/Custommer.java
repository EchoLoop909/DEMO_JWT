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

    @Column(length = 10)
    private String ten;

    @Column(length = 20)
    private String ho;

    @Column(length = 50)
    private String diachi;

    @Column(length = 12, nullable = false)
    private String cccd;

    @Column(length = 15, nullable = false)
    private String sodienthoai;

    @Column(length = 20, nullable = false)
    private String sothenganhang;
}
