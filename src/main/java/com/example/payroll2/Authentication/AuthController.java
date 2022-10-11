package com.example.payroll2.Authentication;

import com.example.payroll2.Config.WebSecurityConfig;
import com.example.payroll2.Repositories.AppUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Logging in, generating access token, refreshing access token")

public class AuthController {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${refresh.secret}")
    private String refreshTokenSecret;
    private final AppUserRepository appUserRepository;

    public AuthController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest){
        var user = appUserRepository.findByUsername(loginRequest.getUsername());
        if(user!= null){
            if(WebSecurityConfig.passwordEncoder().matches(loginRequest.getPassword(), user.getPassword())){
              var accessToken =  generateAccessToken(user.getUsername());
              String refreshToken = generateToken(refreshTokenSecret, user.getUsername(), Date.from(Instant.now().plusSeconds(86_400)));
                return ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken));

            }else {
                return ResponseEntity.status(401).body("Incorrect password ");
            }
        }
        return ResponseEntity.ok().body("User doesn't exist");
    }

    @RequestMapping(value = "/refresh-token", method = RequestMethod.POST)
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        try{
            var jwt = Jwts.parserBuilder().
                    setSigningKey(refreshTokenSecret.getBytes()).build().parseClaimsJws(refreshTokenRequest.getToken());
            var accessToken = this.generateAccessToken(jwt.getBody().getSubject());
            String refreshToken = generateToken(refreshTokenSecret, jwt.getBody().getSubject(), Date.from(Instant.now().plusSeconds(86_400)));
            return ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken));
        }catch (Exception e){
            return  ResponseEntity.status(401).body("Invalid refresh Token");
        }

    }

    private String generateToken(String secret, String username, Date expirationTime){
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        var refreshToken = Jwts.builder().setSubject(username).setExpiration(expirationTime).signWith(key).compact();
        return refreshToken;
    }

    private String generateAccessToken(String username){
        var expirationTime = Date.from(Instant.now().plusSeconds(3600));
        String token = generateToken(jwtSecret,username,expirationTime);
        return token;
    }
}
