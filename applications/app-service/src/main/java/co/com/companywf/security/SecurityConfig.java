package co.com.companywf.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthManager authManager;
    private final SecurityContextRepository securityContextRepository;

    @Bean
    public MapReactiveUserDetailsService user() throws Exception {
        List<UserDetails> users = List.of(
                User.withUsername("user").password("user")
                        .roles("USER").build(),
                User.withUsername("admin").password("admin")
                        .roles("USER", "ADMIN").build(),
                User.withUsername("operator").password("operator")
                        .roles("OPERATOR").build());
        return new MapReactiveUserDetailsService(users);
    }

    @Bean
    public SecurityWebFilterChain filter(ServerHttpSecurity http) throws Exception{
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authenticationManager(authManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(auth -> auth.pathMatchers(HttpMethod.POST, "/api/signup").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/api/v1/user","/api/v1/location").hasAnyRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/api/v1/videogame").hasAnyRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/api/v1/developer/{id}","/api/v1/status/{id}","/api/v1/gender/{id}","/api/v1/videogame/{id}").hasAnyRole("ADMIN")
                        .pathMatchers("/api/v1/**").authenticated()
                        .anyExchange().permitAll())
                        .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
