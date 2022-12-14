package com.example.payroll2.Middleware;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var username = servletRequest.getParameter("username");
        var password = servletRequest.getParameter("password");

        if(username!= null && password!=null){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
