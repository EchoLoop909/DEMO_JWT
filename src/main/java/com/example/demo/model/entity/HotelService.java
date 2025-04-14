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



    @Column(name = "service_code", length = 10, nullable = false)  // Mã dịch vụ
    private String serviceCode;

    @Column(name = "service_name", nullable = false)  // Tên dịch vụ
    private String serviceName;

    @Column(name = "price", nullable = false)  // Giá dịch vụ
    private Double price;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "madichvu", nullable = false)
//    private HotelService service;
}
