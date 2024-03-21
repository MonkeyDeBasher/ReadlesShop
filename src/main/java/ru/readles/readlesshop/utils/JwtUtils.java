package ru.readles.readlesshop.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.readles.readlesshop.repository.UsersRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.tokenLifetime}")
    private int tokenLifetime;

    @Autowired
    private UsersRepository usersRepository;
    public String generation(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles",rolesList);
        claims.put("userId", usersRepository.findByLogin(userDetails.getUsername()).getId_user());
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + tokenLifetime);
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(issuedDate)
                .expiration(expiredDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String getLogin(String token){
        return getAllClaimsFromToken(token).getSubject();
    }
    public Long getUserId(String token){
        return getAllClaimsFromToken(token).get("userId", Long.class);
    }
    public List<String> getRoles(String token){
        return getAllClaimsFromToken(token).get("roles",List.class);
    }
    public String getSecret() {
        return secret;
    }

    public int getTokenLifetime() {
        return tokenLifetime;
    }

}