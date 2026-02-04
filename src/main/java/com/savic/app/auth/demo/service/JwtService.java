package com.savic.app.auth.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    
    private final String SECRET_KEY = "4d7aca78b1d09466a1c2f9e35c2a1f9b8d2f3e1a7c6b4d9e8f1a2b3c4d5e6f7a";
    
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    //Metoda za izvlacenja username-a iz tokena
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    
    //Metoda za izvlačenje proizvoljnog podatka iz tokena
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    //Metoda za validaciju tokena
    public boolean isTokenValid(String token, String username){
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username)) && !isTokenExpired(token);
    }
    
    //provera da li je token istekao
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    
    //Parsiranje svih podataka iz tokena
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}