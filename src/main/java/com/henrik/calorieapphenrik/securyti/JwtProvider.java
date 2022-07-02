package com.henrik.calorieapphenrik.securyti;

import com.henrik.calorieapphenrik.Person.entity.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class JwtProvider {

    @Value("${security.jwt.secret-key}")
    private byte[] secret;

    @Value("#{${security.jwt.expire-in-mins} * 60000}")
    private long validityMillis;

    public String createToken(Person user) {

        Date now = new Date();

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secret), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", "JWT")
                .setIssuer("recipe-api")
                .setAudience("recipe-front")
                .setIssuedAt(now)
                .setSubject(user.getUsername())
                .setExpiration(new Date(now.getTime() + validityMillis))
                .claim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .compact();

    }

    public UsernamePasswordAuthenticationToken getAuthentication(String jwt){

        Jws<Claims> parseJwt = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(jwt);

        String username = parseJwt.getBody().getSubject();

        List<GrantedAuthority> roles = ((List<String>) parseJwt.getBody()
                .get("roles"))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        if(StringUtils.isNotEmpty(username)){
            return new UsernamePasswordAuthenticationToken(username, null, roles);
        }
        return null;
    }
}
