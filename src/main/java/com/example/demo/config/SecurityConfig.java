//package com.example.demo.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import jakarta.servlet.*;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//
//public class SecurityConfig {
//    private final JwtAuthenticationFilter jwtFilter;
//    private final UserDetailsService userDetailsService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable) // disable CSRF
//                .cors(Customizer.withDefaults()) // enable CORS (dùng CorsConfigurationSource nếu cần cấu hình chi tiết)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//                //cau hinh quy tac xac thuc request
//                .authorizeHttpRequests(auth -> auth
//                        // Cho phép truy cập không cần xác thực với các endpoint bắt đầu bằng "/api/auth/**".
//                        .requestMatchers("/api/auth/**").permitAll()
//                        //moi request khac deu can phai xac thuc
//                        .anyRequest().authenticated()
//                         )
//                // Thiết lập phiên làm việc thành trạng thái "stateless" (không lưu trữ trạng thái session).
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                // Sử dụng AuthenticationProvider đã cấu hình (DaoAuthenticationProvider).
//                .authenticationProvider(authenticationProvider());
//                // Thêm filter xử lý JWT trước UsernamePasswordAuthenticationFilter.
//                http.addFilterBefore((Filter) jwtFilter, UsernamePasswordAuthenticationFilter.class); // add filter đúng cách
//
//        return http.build(); // Trả về một `SecurityFilterChain` sau khi hoàn tất cấu hình.
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        // DaoAuthenticationProvider: Dùng để xác thực với cơ sở dữ liệu.
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService); // Cung cấp UserDetailsService để lấy thông tin người dùng từ DB.
//        provider.setPasswordEncoder(passwordEncoder()); // Cài đặt PasswordEncoder để mã hóa và kiểm tra mật khẩu.
//        return provider; // Trả về AuthenticationProvider đã cấu hình.
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // BCryptPasswordEncoder: Mã hóa mật khẩu bằng thuật toán BCrypt.
//        return new BCryptPasswordEncoder();
//    }
//}
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter, UserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
