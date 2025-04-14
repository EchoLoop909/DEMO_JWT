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

    @Column(name = "discount_code", length = 20, nullable = false)  // Mã chương trình giảm giá
    private String discountCode;

    @Column(name = "discount_percentage", nullable = false)  // Tỉ lệ chiết khấu (%)
    private Double discountPercentage;

    @Column(name = "start_date", nullable = false)  // Ngày bắt đầu
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)  // Ngày kết thúc
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", nullable = false)  // Tham chiếu đến RoomType, đổi tên cột cho phù hợp
    private RoomType roomType;
}
