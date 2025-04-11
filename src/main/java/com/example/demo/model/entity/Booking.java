package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.demo.model.entity.Custommer;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "madatphong", length = 10)
    private String madatphong;

    // Liên kết đến nhân viên
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manhanvien", nullable = false)
    private Staff staff;

    // Liên kết đến khách hàng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "makhachhang", nullable = false)
    private Custommer custommer;

    @Column(name = "ngaydatphong", nullable = false)
    private LocalDate ngaydatphong;

    @Column(name = "songayo", nullable = false)
    private Integer songayo;

    @Column(name = "checkindate", nullable = false)
    private LocalDate checkindate;

    @Column(name = "checkoutdate", nullable = false)
    private LocalDate checkoutdate;

    @Column(name = "phuongthucthanhtoan", length = 20, nullable = false)
    private String phuongthucthanhtoan;

    @Column(name = "coc", nullable = false)
    private Double coc;

    @Column(name = "tongtien", nullable = false)
    private Float tongtien;
}
