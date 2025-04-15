package com.example.demo.repository;

import com.example.demo.model.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(value = "SELECT * FROM staff WHERE (:firstName IS NULL OR first_name LIKE %:firstName%) " +
            "AND (:lastName IS NULL OR last_name LIKE %:lastName%) " +
            "AND (:id IS NULL OR id = :id)", nativeQuery = true)
    Page<Staff> findStaff(@Param("id") Long id,
                          @Param("firstName") String firstName,
                          @Param("lastName") String lastName,
                          Pageable pageable);


}
