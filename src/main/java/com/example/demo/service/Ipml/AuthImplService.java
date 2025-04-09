package com.example.demo.service.Ipml;

import com.example.demo.config.JwtUtils;
import com.example.demo.model.dto.request.AuthenticationRequest;
import com.example.demo.model.dto.request.Register;
import com.example.demo.model.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

@Service
public class AuthImplService implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthImplService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password){
        try {
            // Xác thực username/password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Lấy thông tin user
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Tạo và trả về token
            return jwtUtils.generateToken(userDetails);

        } catch (Exception e) {
            throw new AppException(ErrorCode.UNAUTHORIZED, "Sai tài khoản hoặc mật khẩu");
        }
    }

    @Override
    public String register(Register request) {
        try{
           Optional<User> userName = userRepository.findByUsername(request.getUsername());

           if(userName != null && userName.isPresent()){
               throw new AppException(ErrorCode.USER_EXISTED,"UserName already exist");
           }

           User user = new User();

           user.setUsername(request.getUsername());
           user.setPassword(passwordEncoder.encode(request.getPassword()));
           user.setRole("ROLE_USER");

           userRepository.save(user);

           return jwtUtils.generateToken(user);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNAUTHORIZED, "Sai tài khoản hoặc mật khẩu");
        }
    }
}
