package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "Username")
        })
public class User implements UserDetails, Serializable {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @NotBlank
    @Column(name = "Username")
    private String username;

    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 120)
    @Column(name = "Password")
    private String password;

    @Column(name = "Role") // 👈 Không dùng enum nữa
    private String role; // VD: ROLE_USER, ROLE_ADMIN
}
