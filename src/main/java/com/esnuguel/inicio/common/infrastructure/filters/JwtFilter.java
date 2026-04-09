package com.esnuguel.inicio.common.infrastructure.filters;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.esnuguel.inicio.common.infrastructure.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authorization=request.getHeader("Authorization");
        if(authorization==null || !authorization.startsWith("Bearer ")){
            log.error("Token no encontrado");
            filterChain.doFilter(request, response);
            return;
        }
        
        String token=authorization.substring(7);
        //Verificar si jwt esta caducado
        boolean isTokenExpired=jwtService.isTokenExpired(token);
        boolean canBeTokenRenewed=jwtService.canBeTokenRefreshed(token);

        if(isTokenExpired && !canBeTokenRenewed){
            log.error("Token expirado");
            filterChain.doFilter(request, response);
            return;
        }




        String username=jwtService.getUsername(token);

        if(username==null || SecurityContextHolder.getContext().getAuthentication()!=null){
            log.error("Invalid token or user alreaty authenticated");
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails= User.withDefaultPasswordEncoder().username(username)
        .password("password").roles("USER").build();

        if(isTokenExpired && canBeTokenRenewed){
            String renewToken=jwtService.renewToken(token, userDetails);
            response.setHeader("Authorization", "Bearer "+renewToken);
        }

        UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
    
}
