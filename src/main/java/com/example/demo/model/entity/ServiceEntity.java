package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String madichvu; // Mã dịch vụ - Khóa chính

    @Column(length = 20, nullable = false)
    private String tendichvu; // Tên dịch vụ

    @Column(nullable = false)
    private Double gia; // Giá dịch vụ
}