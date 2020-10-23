package ru.sysout.jwt.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.sessionTime}")
    private long sessionTime;

    // генерация токена (кладем в него имя пользователя и authorities)
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String commaSeparatedListOfAuthorities=  userDetails.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(","));
        claims.put("authorities", commaSeparatedListOfAuthorities);
        return createToken(claims, userDetails.getUsername());
    }

    //извлечение имени пользователя из токена (внутри валидация токена)
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //извлечение authorities (внутри валидация токена)
    public String extractAuthorities(String token) {
        Function<Claims, String> claimsListFunction = claims -> {
            return (String)claims.get("authorities");
        };
        return extractClaim(token, claimsListFunction);
    }



    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expireTimeFromNow())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


    private Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis() + sessionTime);
    }
}