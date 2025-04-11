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

    @Column(length = 20)
    private String machitietphong;

    // Khóa ngoại đến bảng phong
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maphong", nullable = false)
    private Room room;

    // Khóa ngoại đến bảng datphong
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "madatphong", nullable = false)
    private Booking booking;
}
