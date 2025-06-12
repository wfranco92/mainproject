package co.com.companywf.security;

import co.com.companywf.model.exception.AuthenticacionException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthManager implements ReactiveAuthenticationManager {

    @Value("${secretKey}")
    private String secretKey;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                        .build()
                        .parseSignedClaims(auth.getCredentials().toString().replace("Bearer ", ""))
                        .getPayload())
                .switchIfEmpty(Mono.empty())
                .onErrorResume(throwable -> Mono.error(new AuthenticacionException(throwable.getMessage())))
                .map(claims -> new UsernamePasswordAuthenticationToken(
                        claims.getSubject(), null,
                        ((List<String>) claims.get("authorities", List.class)).stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList())));
    }
}
