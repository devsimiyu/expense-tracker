package com.devsimiyu.expensetracker.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.devsimiyu.expensetracker.security.SecurityUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;

    private Claims claims;
    private Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public Boolean validateToken(String token) {
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        } catch (Exception e) { logger.error(e.getMessage()); }

        return claims == null ? false : !isTokenExpired();
    }

    public String generateToken(SecurityUserDetails securityUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(
            "role",
            securityUserDetails.getAuthorities()
        );
        Calendar calendar = Calendar.getInstance();
        Date issueDate = calendar.getTime();
        calendar.add(Calendar.DATE, 30);
        Date expiryDate = calendar.getTime();
        String token =  Jwts.builder()
            .setClaims(claims)
            .setSubject(securityUserDetails.getUsername())
            .setIssuedAt(issueDate)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();

        return token;
    }

    public String getUsername() {
        return claims.getSubject();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return claims.get("role", Collection.class);
    }

    private Boolean isTokenExpired() {
        return claims.getExpiration().before(new Date());
    }
}
