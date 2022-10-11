package com.example.payroll2.Config;

import com.example.payroll2.Middleware.CustomFilter;
import com.example.payroll2.Middleware.JwtTokenFilter;
import com.example.payroll2.Services.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
     private final AppUserService appUserService;
     private final CustomFilter customFilter;
     private final JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests().
                antMatchers("/users/**", "/roles/**", "/auth/**").
                permitAll().
                anyRequest().authenticated().and().
                addFilterBefore(jwtTokenFilter, BasicAuthenticationFilter.class).
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic();
    }

    public WebSecurityConfig(AppUserService appUserService, CustomFilter customFilter, JwtTokenFilter jwtTokenFilter) {
        this.appUserService = appUserService;
        this.customFilter = customFilter;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder());
    }

   @Bean
   public static PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
