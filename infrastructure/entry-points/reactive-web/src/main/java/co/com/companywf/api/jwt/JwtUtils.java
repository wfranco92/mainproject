package co.com.companywf.api.jwt;

import co.com.companywf.model.role.Role;
import co.com.companywf.model.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {


    private static final long EXPIRE_TIME = 3_600_000;
    @Value("${secretKey}")
    private String secretKey;

    public String getToken(User details) {
        return Jwts.builder()
                .subject(details.getUsername())
                .issuedAt(new Date())
                .claim("authorities", details.getRoles().stream().map(Role::getRolName).toList())
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

    }
}
