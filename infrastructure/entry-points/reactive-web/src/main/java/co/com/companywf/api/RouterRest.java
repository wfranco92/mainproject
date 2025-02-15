package co.com.companywf.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/v1/videogame"), handler::listenAllVideoGames)
                .andRoute(GET("/api/v1/videogame/{id}"), handler::listenPOSTUseCase)
                .andRoute(POST("/api/v1/videogame"), handler::listenSaveVideoGame)
                .andRoute(POST("/api/v1/Gender"), handler::listenSaveGender);
    }
}
