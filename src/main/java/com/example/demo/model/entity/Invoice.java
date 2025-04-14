package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    @Column(name = "totalAmount")
    private BigDecimal totalAmount;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "paymentDate")
    private java.time.LocalDateTime paymentDate;
}
