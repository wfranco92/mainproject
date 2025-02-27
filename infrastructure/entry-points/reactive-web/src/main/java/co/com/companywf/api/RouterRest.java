package co.com.companywf.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/v1/videogame"), handler::listenAllVideoGames)
                .andRoute(GET("/api/v1/videogame/{id}"), handler::listenGetVideoGameById)
                .andRoute(POST("/api/v1/videogame"), handler::listenSaveVideoGame)
                .andRoute(PUT("/api/v1/videogame/{id}"), handler::listenPUTVideoGame)
                .andRoute(DELETE("/api/v1/videogame/{id}"), handler::listenDeleteVideoGame)
                .andRoute(POST("/api/v1/gender"), handler::listenSaveGender)
                .andRoute(GET("/api/v1/gender/{id}"), handler::listenGetGenderById)
                .andRoute(GET("/api/v1/gender"), handler::listenGetAllGender)
                .andRoute(PUT("/api/v1/gender/{id}"), handler::listenPutGender)
                .andRoute(POST("/api/v1/status"), handler::listenSaveStatus)
                .andRoute(GET("/api/v1/status/{id}"), handler::listenGetStatusById)
                .andRoute(GET("/api/v1/status"), handler::listenGetAllStatus)
                .andRoute(PUT("/api/v1/status/{id}"), handler::listenPUTStatus)
                .andRoute(POST("/api/v1/developer"), handler::listenSaveDeveloper)
                .andRoute(GET("/api/v1/developer/{id}"), handler::listenGetDeveloperById)
                .andRoute(GET("/api/v1/developer"), handler::listenGetAllDeveloper)
                .andRoute(PUT("/api/v1/developer/{id}"), handler::listenPUTDeveloper)
                .andRoute(POST("/api/v1/location"), handler::listenSaveLocation)
                .andRoute(GET("/api/v1/location/{id}"), handler::listenGetLocationById)
                .andRoute(GET("/api/v1/location"), handler::listenGetAllLocation)
                .andRoute(PUT("/api/v1/location/{id}"), handler::listenPUTLocation)
                .andRoute(RequestPredicates.all(), handler::handleNotFound);
    }
}
