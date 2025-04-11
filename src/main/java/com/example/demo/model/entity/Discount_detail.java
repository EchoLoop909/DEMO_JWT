package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "discount_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Discount_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String mactgg;

    @Column(nullable = false)
    private Double tiletrietkhau;

    @Column(nullable = false)
    private LocalDate ngaybatdau;

    @Column(nullable = false)
    private LocalDate ngayketthuc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maloaiphong", nullable = false)
    private RoomType roomType; // Tham chiếu đến entity RoomType
}
