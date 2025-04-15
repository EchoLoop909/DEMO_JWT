package com.example.demo.repository;

import com.example.demo.model.entity.Custommer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustommerRepository extends JpaRepository<Custommer, Long> {
    Optional<Custommer> findByFullName(String fullName);

    @Query(value = "SELECT * FROM custommer WHERE (:id IS NULL OR id LIKE %:id%) ", nativeQuery = true)
    Page<Custommer> findCustommer(@Param("id") Long id,
                          Pageable pageable);

}
