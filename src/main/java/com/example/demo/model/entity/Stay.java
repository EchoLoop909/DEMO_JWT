package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Stay")
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "actualCheckIn")
    private java.time.LocalDateTime actualCheckIn;

    @Column(name = "actualCheckOut")
    private java.time.LocalDateTime actualCheckOut;

    @OneToOne
    @JoinColumn(name = "invoice")
    private Invoice invoice;
}
