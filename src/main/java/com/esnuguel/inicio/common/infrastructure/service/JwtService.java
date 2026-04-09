package com.esnuguel.inicio.common.infrastructure.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Base64.Decoder;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private static final String SECRET_KEY = "AB3E2341C4D5678901234567890ABCDEAB3E2341C4D5678901234567890ABCDE";
    private static final long REFRESH_TOKEN_WINDOW= 1000*60*60*24*7; // 7 días -> tiempo adicional para renovar el token
    private static final long TOKEN_EXPIRATION= 1000*60*60*24; // 24 horas
    //private static final long TOKEN_EXPIRATION= 1000; // 1ms

        public String generateToken(UserDetails userDetails){
            Map<String, Object> claims=Map.of("authorities", userDetails.getAuthorities()
            .stream().map(GrantedAuthority::getAuthority).toList());
            return generateToken(claims,userDetails.getUsername());
        }
    
        //Subject es el username del usuario
        public String generateToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(getSignKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey(){
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaims(String token){
        try {
            return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch(UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e){
            throw new RuntimeException("Token inválido o mal formado",e);
        }
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsMapper){
        Claims claims=getAllClaims(token);
        return claimsMapper.apply(claims);
    }

    public String getUsername(String token){
        //return getAllClaims(token).getSubject();
        return getClaim(token, Claims::getSubject);
    }

    public Date getExpirationDate(String token){
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return getExpirationDate(token).before(new Date());
    }

    public boolean canBeTokenRefreshed(String token){
        return getExpirationDate(token).before(new Date(System.currentTimeMillis()+REFRESH_TOKEN_WINDOW));
    }

    public String renewToken(String token,UserDetails userDetails){
        if(!canBeTokenRefreshed(token)){
            throw new RuntimeException("El token no puede ser renovado");
        }
        return generateToken(userDetails);
    }
}
