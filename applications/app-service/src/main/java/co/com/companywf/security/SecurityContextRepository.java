package co.com.companywf.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private final AuthManager authManager;

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader-> authHeader.startsWith("Bearer "))
                .flatMap(auth-> this.authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(auth.substring(7), auth.substring(7)))
                        .map(SecurityContextImpl::new)
                );
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }
}
