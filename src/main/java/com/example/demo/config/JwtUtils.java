//package com.example.demo.config;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//
//@Component
//public class JwtUtils {
//
//    @Value("${bkav.app.jwtSecret}")
//    private String jwtSecret;
//
//    @Value("${bkav.app.jwtExpirationMs}")
//    private int jwtExpirationMs;
//
//    //tao jwt dua tren thong tin nguoi dung
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())//dat username lam subject cua token
//                .setIssuedAt(new Date())//thoi gian phat hanh token
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))//thoi gian het han cua token
//                .signWith(SignatureAlgorithm.HS512, jwtSecret) //ky token voi thuat toan HS512 va secretToken
//                .compact();//tra ve chuoi token
//    }
//
//    //trich xuat username tu token
//    public String extractToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(jwtSecret)//thiet lao secret key de giai ma tokem
//                .parseClaimsJws(token)// Parse token va lay thong tin trong (claims)
//                .getBody()
//                .getSubject(); // Lấy subject (username) từ claims
//    }
//
//    //kiem tra token co hop le hay khong
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractToken(token);//lay username tu token
//        return (username.equals(userDetails.getUsername()) && !token.equals(token));//so sanh va kiem tra han
//    }
//
//    //kiem tra token da het han hay chua
//    public boolean isTokenExpired(String token) {
//        Date expitation = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody()
//                .getExpiration();//lay ngay het han tu claims
//        return expitation.before(new Date());//so sanh voi thoi gian hien tai
//    }
//}

package com.example.demo.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${bkav.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bkav.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String extractToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractToken(token);
        return username.equals(userDetails.getUsername());
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
