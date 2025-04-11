package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String maphong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maloaiphong", nullable = false)
    private RoomType roomType; // Khóa ngoại tới Loại phòng

    @Column(length = 10, nullable = false)
    private String sophong; // Số phòng
}
