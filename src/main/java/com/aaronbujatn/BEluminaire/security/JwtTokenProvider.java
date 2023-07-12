package com.aaronbujatn.BEluminaire.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import static com.aaronbujatn.BEluminaire.security.SecurityConstants.EXPIRATION_TIME;
import static com.aaronbujatn.BEluminaire.security.SecurityConstants.JWT_SECRET;

@Component
@Slf4j
public class JwtTokenProvider {

    //Generate token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentDate.getTime() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    //get username from jwt token
    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    //validate token
    public Boolean isValidToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
                    return true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Jwt was expire or incorrect");
        }
    }


}
