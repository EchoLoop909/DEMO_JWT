package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_detail_code", length = 20, nullable = false)
    private String roomDetailCode;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    // Khóa ngoại đến bảng booking
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)  // Đảm bảo cột này tồn tại trong bảng room_detail
    private Booking booking;
}
