package com.example.payroll2.Middleware;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var header =request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer ")){
            var token = header.substring(7);
            Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            try{
                var jwt = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(jwt.getBody().getSubject(), null, new ArrayList<>());
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(securityContext);
            }catch (Exception e){
                response.setStatus(401);
                response.getWriter().println("Access Denied");
            }
        }
     filterChain.doFilter(request,response);
    }

}
