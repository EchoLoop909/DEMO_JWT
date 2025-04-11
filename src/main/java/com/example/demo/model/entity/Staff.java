package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String manhanvien;

    @Column(length = 10)
    private String ten;

    @Column(length = 20)
    private String ho;

    @Column(length = 20)
    private String chucvu;

    public Staff(Long id, String manhanvien, String ten, String ho, String chucvu, String diachi, String sodienthoai) {
        this.id = id;
        this.manhanvien = manhanvien;
        this.ten = ten;
        this.ho = ho;
        this.chucvu = chucvu;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
    }

    @Column(length = 50)
    private String diachi;

    @Column(length = 15, nullable = false, unique = true)
    private String sodienthoai;
}
