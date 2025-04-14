package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "EmployeeShift")
public class EmployeeShift {
    @Id
    @Column(name = "employeeId")
    private Long employeeId;

    @Id
    @Column(name = "shiftId")
    private Long shiftId;

    @Id
    @Column(name = "date")
    private java.time.LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employeeId", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "shiftId", insertable = false, updatable = false)
    private Shift shift;
}
