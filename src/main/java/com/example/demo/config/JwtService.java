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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "hieph@hieph-B360M-D2V:~$ openssl rand -base64 64\n" +
            "Mk25REJvvi47dZ+h9T6QOJ0bz6PcsPvHtT8vKgYOhuZAEOJdAY9DoHPM8U0nFZ4Z\n" +
            "LFbM+TvMdSacQVBMsuQN/Q==\n";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token) {
        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
                .parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
