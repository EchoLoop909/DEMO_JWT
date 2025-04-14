package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Custommer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")  // Đổi tên cột
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "room_id")  // Đổi tên cột
    private Room room;

    @Column(name = "checkInDate")
    private java.time.LocalDateTime checkInDate;

    @Column(name = "checkOutDate")
    private java.time.LocalDateTime checkOutDate;

    @Column(name = "status")
    private String status;
}
