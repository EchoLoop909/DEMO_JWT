package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_code", length = 10, nullable = false, unique = true)  // Mã nhân viên
    private String employeeCode;

    @Column(name = "first_name", length = 20, nullable = false)  // Tên
    private String firstName;

    @Column(name = "last_name", length = 20, nullable = false)  // Họ
    private String lastName;

    @Column(name = "position", length = 20, nullable = false)  // Chức vụ
    private String position;

    @Column(name = "address", length = 50)  // Địa chỉ
    private String address;

    @Column(name = "phone_number", length = 15, nullable = false, unique = true)  // Số điện thoại
    private String phoneNumber;


}
