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
public class HotelService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String madichvu;

    private String tendichvu;

    private Double gia;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "madichvu", nullable = false)
//    private HotelService service;
}
