package com.curso.dcortes.UsersService.infrastructure.config;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Function;


@Component
public class JwtUtilities {
    private String secret = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    private Long jwtExpiration = 36000000L;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractUsername(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName , List<String> roles) {

        return Jwts.builder().setSubject(userName).claim("role",roles).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature.");
            System.out.println("Invalid JWT signature trace: {" + e + "}");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token.");
            System.out.println("Invalid JWT token trace: {" + e + "}");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
            System.out.println("Expired JWT token trace: {" + e + "}");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
            System.out.println("Unsupported JWT token trace: {" + e + "}");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token compact of handler are invalid.");
            System.out.println("JWT token compact of handler are invalid trace: {" + e + "}");
        }
        return false;
    }

    public String getToken (HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
        {return bearerToken.substring(7,bearerToken.length()); } // The part after "Bearer "
        return null;
    }

}
