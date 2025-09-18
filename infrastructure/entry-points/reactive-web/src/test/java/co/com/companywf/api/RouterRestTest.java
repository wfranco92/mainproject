package co.com.companywf.api;

import co.com.companywf.api.dto.*;
import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.DeveloperRequest;
import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.LocationRequest;
import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.StatusRequest;
import co.com.companywf.model.user.User;
import co.com.companywf.model.user.UserRequest;
import co.com.companywf.model.videogame.ResponseStatistics;
import co.com.companywf.model.videogame.VideoGameRequest;
import co.com.companywf.model.videogame.Videogame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class RouterRestTest {

    private HandlerTestHelperFactory.HandlerTestContext context;

    @BeforeEach
    void setup() {
        context = HandlerTestHelperFactory.createContext();
    }

    @Test
    void testLlistenAllVideoGamesUseCase() {

        when(context.getAllVideoGamesUseCase.execute(any(), any())).thenReturn(Flux.just(Videogame.builder().build()));

        context.webTestClient.get()
                .uri("/api/v1/videogame")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isNotEmpty();
                        }
                );
    }

    @Test
    void testlistenGetVideoGameByIdRouteTest() {

        Videogame videoGame = Videogame.builder().name("Zelda").build();
        VideoGameResponseDTO responseDTO = VideoGameResponseDTO.builder().name("Zelda").build();

        when(context.getVideoGameByIdUseCase.execute("abc")).thenReturn(Mono.just(videoGame));
        when(context.mapper.map(eq(videoGame), eq(VideoGameResponseDTO.class))).thenReturn(responseDTO);

        String id = "abc";

        context.webTestClient.get()
                .uri("/api/v1/videogame/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Zelda");
    }

    @Test
    void testlistenSaveVideoGameRoueTest(){

        VideoGameRequest videoGameRequest = VideoGameRequest.builder().name("Zelda").build();
        VideoGameRequestDTO videoGameRequestDTO = VideoGameRequestDTO.builder().name("Zelda").build();
        Videogame videoGame = Videogame.builder().name("Zelda").build();
        VideoGameResponseDTO responseDTO = VideoGameResponseDTO.builder().name("Zelda").build();


        when(context.mapper.map(any(VideoGameRequestDTO.class), eq(VideoGameRequest.class))).thenReturn(videoGameRequest);

        when(context.saveAllVideoGamesUseCase.execute(List.of(videoGameRequest))).thenReturn(Flux.just(videoGame));

        when(context.mapper.map(eq(videoGame), eq(VideoGameResponseDTO.class))).thenReturn(responseDTO);

        context.webTestClient.post()
                .uri("/api/v1/videogame")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(videoGameRequestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Zelda");
    }

    @Test
    void testlistenPUTVideoGameRoueTest(){

        VideoGameRequest videoGameRequest = VideoGameRequest.builder().name("Zelda modified").build();
        VideoGameRequestDTO videoGameRequestDTO = VideoGameRequestDTO.builder().name("Zelda modified").build();
        Videogame videoGame = Videogame.builder().name("Zelda modified").build();
        VideoGameResponseDTO responseDTO = VideoGameResponseDTO.builder().name("Zelda modified").build();


        when(context.mapper.map(any(VideoGameRequestDTO.class), eq(VideoGameRequest.class))).thenReturn(videoGameRequest);

        when(context.updateVideoGameUseCase.execute("123",videoGameRequest)).thenReturn(Mono.just(videoGame));

        when(context.mapper.map(eq(videoGame), eq(VideoGameResponseDTO.class))).thenReturn(responseDTO);

        context.webTestClient.put()
                .uri("/api/v1/videogame/{id}", "123")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(videoGameRequestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Zelda modified");
    }

    @Test
    void testlistenDeleteVideoGameRoueTest(){

        Videogame videoGame = Videogame.builder().name("Zelda to eliminate").build();
        VideoGameResponseDTO responseDTO = VideoGameResponseDTO.builder().name("Zelda to eliminate").build();

        when(context.deleteVideoGameUseCase.execute("123")).thenReturn(Mono.just(videoGame));

        when(context.mapper.map(eq(videoGame), eq(VideoGameResponseDTO.class))).thenReturn(responseDTO);

        context.webTestClient.delete()
                .uri("/api/v1/videogame/{id}", "123")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Zelda to eliminate");
    }

    @Test
    void testlistenSaveGenderRoueTest(){

        GenderRequestDTO genderRequestDTO = GenderRequestDTO.builder().name("ROL").build();
        GenderRequest genderRequest = GenderRequest.builder().name("ROL").build();
        Gender gender = Gender.builder().name("ROL").build();

        when(context.saveGenderUseCase.execute(genderRequest)).thenReturn(Mono.just(gender));

        when(context.mapper.map(eq(genderRequestDTO), eq(GenderRequest.class))).thenReturn(genderRequest);

        context.webTestClient.post()
                .uri("/api/v1/gender")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(genderRequestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("ROL");
    }

    @Test
    void testlistenGetGenderByIdRoueTest(){

        Gender gender = Gender.builder().name("ROL").build();

        when(context.getGenderByIdUseCase.execute("1")).thenReturn(Mono.just(gender));

        context.webTestClient.get()
                .uri("/api/v1/gender/{id}", "1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("ROL");
    }

    @Test
    void testlistenGetAllGenderRoueTest(){

        Gender gender = Gender.builder().name("ROL").build();

        when(context.getAllGenderUseCase.execute()).thenReturn(Flux.just(gender));

        context.webTestClient.get()
                .uri("/api/v1/gender")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("ROL");
    }

    @Test
    void testlistenPutGenderRoueTest(){

        GenderRequestDTO genderRequestDTO = GenderRequestDTO.builder().name("modified").build();
        GenderRequest genderRequest = GenderRequest.builder().name("modified").build();
        Gender gender = Gender.builder().name("modified").build();

        when(context.updateGenderUseCase.execute("1", genderRequest)).thenReturn(Mono.just(gender));
        when(context.mapper.map(genderRequestDTO, GenderRequest.class)).thenReturn(genderRequest);

        context.webTestClient.put()
                .uri("/api/v1/gender/{id}", "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(genderRequestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("modified");
    }

    @Test
    void testlistenSaveStatusRoueTest(){
        StatusRequestDTO requestDTO = StatusRequestDTO.builder().description("played").build();
        StatusRequest request = StatusRequest.builder().description("played").build();
        Status status = Status.builder().description("played").build();

        when(context.saveStatusUseCase.execute(request)).thenReturn(Mono.just(status));

        when(context.mapper.map(any(StatusRequestDTO.class), eq(StatusRequest.class))).thenReturn(request);

        //when(context.mapper.map(eq(requestDTO), eq(StatusRequest.class))).thenReturn(request);

        context.webTestClient.post()
                .uri("/api/v1/status")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.description").isEqualTo("played");
    }

    @Test
    void testlistenGetStatusByIdRoueTest(){
        Status status = Status.builder().description("played").build();

        when(context.getStatusByIdUseCase.execute("1")).thenReturn(Mono.just(status));

        context.webTestClient.get()
                .uri("/api/v1/status/{id}", "1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.description").isEqualTo("played");
    }

    @Test
    void testllistenGetAllStatusRoueTest(){
        Status status = Status.builder().description("played").build();

        when(context.getAllStatusUseCase.execute()).thenReturn(Flux.just(status));

        context.webTestClient.get()
                .uri("/api/v1/status")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].description").isEqualTo("played");
    }

    @Test
    void testllistenPUTStatusRoueTest(){
        StatusRequestDTO requestDTO = StatusRequestDTO.builder().description("played").build();
        StatusRequest request = StatusRequest.builder().description("played").build();
        Status status = Status.builder().description("played").build();

        when(context.updateStatusUseCase.execute("1", request)).thenReturn(Mono.just(status));
        when(context.mapper.map(any(StatusRequestDTO.class), eq(StatusRequest.class))).thenReturn(request);

        context.webTestClient.put()
                .uri("/api/v1/status/{id}", "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.description").isEqualTo("played");
    }

    @Test
    void testllistenGETDeveloperRouteTest(){

        Developer developer = Developer.builder().name("developer").build();

        when(context.getAllDeveloperUseCase.execute("0", "10")).thenReturn(Flux.just(developer));

        context.webTestClient.get()
                .uri("/api/v1/developer")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("developer");
    }

    @Test
    void testListenPOSTDeveloperRouteTest(){

        DeveloperRequestDTO developerRequestDTO = DeveloperRequestDTO.builder().name("develop").build();
        DeveloperRequest developerRequest = DeveloperRequest.builder().name("develop").build();
        Developer developer = Developer.builder().name("develop").build();

        when(context.saveDeveloperUseCase.execute(developerRequest)).thenReturn(Mono.just(developer));
        when(context.mapper.map(any(DeveloperRequestDTO.class), eq(DeveloperRequest.class))).thenReturn(developerRequest);

        context.webTestClient.post()
                .uri("/api/v1/developer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(developerRequestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("develop");
    }

    @Test
    void testllistenGETDeveloperByIdRouteTest(){

        Developer developer = Developer.builder().name("developer").build();

        when(context.getDeveloperByIdUseCase.execute(anyString())).thenReturn(Mono.just(developer));

        context.webTestClient.get()
                .uri("/api/v1/developer/{id}", "1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("developer");
    }

    @Test
    void testllistenPUTDeveloperByIdRouteTest(){

        Developer developer = Developer.builder().name("developerUpdated").build();
        DeveloperRequestDTO developerRequestDTO = DeveloperRequestDTO.builder().name("developerUpdated").build();
        DeveloperRequest developerRequest = DeveloperRequest.builder().name("developerUpdated").build();

        when(context.updateDeveloperUseCase.execute("1", developerRequest)).thenReturn(Mono.just(developer));
        when(context.mapper.map(any(DeveloperRequestDTO.class), eq(DeveloperRequest.class))).thenReturn(developerRequest);

        context.webTestClient.put()
                .uri("/api/v1/developer/{id}", "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(developerRequestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("developerUpdated");
    }

    @Test
    void testllistenPUTLocationByIdRouteTest(){

        Location location = Location.builder().name("locationUpdated").build();
        LocationRequestDTO locationRequestDTO = LocationRequestDTO.builder().name("locationUpdated").build();
        LocationRequest locationRequest = LocationRequest.builder().name("locationUpdated").build();

        when(context.updateLocationUseCase.execute("1", locationRequest)).thenReturn(Mono.just(location));
        when(context.mapper.map(any(LocationRequestDTO.class), eq(LocationRequest.class))).thenReturn(locationRequest);

        context.webTestClient.put()
                .uri("/api/v1/location/{id}", "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(locationRequestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("locationUpdated");
    }

    @Test
    void testllistenPOSTLocationRouteTest(){

        Location location = Location.builder().name("locationCreated").build();
        LocationRequestDTO locationRequestDTO = LocationRequestDTO.builder().name("locationCreated").build();
        LocationRequest locationRequest = LocationRequest.builder().name("locationCreated").build();

        when(context.saveLocationUseCase.execute(locationRequest)).thenReturn(Mono.just(location));
        when(context.mapper.map(any(LocationRequestDTO.class), eq(LocationRequest.class))).thenReturn(locationRequest);

        context.webTestClient.post()
                .uri("/api/v1/location")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(locationRequestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("locationCreated");
    }

    @Test
    void testllistenGETLocationRouteTest(){

        Location location = Location.builder().name("locationCreated").build();
        when(context.getAllLocationUseCase.execute()).thenReturn(Flux.just(location));

        context.webTestClient.get()
                .uri("/api/v1/location")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("locationCreated");
    }

    @Test
    void testllistenGETLocationByIdRouteTest(){

        Location location = Location.builder().name("locationUpdated").build();

        when(context.getLocationByIdUseCase.execute("1")).thenReturn(Mono.just(location));

        context.webTestClient.get()
                .uri("/api/v1/location/{id}", "1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("locationUpdated");
    }

    @Test
    void testllistenStatisticsRouteTest(){

        ResponseStatistics statistics = ResponseStatistics.builder().genders(Map.of("ROL", 1)).build();

        when(context.statisticsUseCase.execute()).thenReturn(Mono.just(statistics));

        context.webTestClient.get()
                .uri("/api/v1/statistics")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.genders").isNotEmpty();
    }

    @Test
    void testlSearchRouteTest(){

        Videogame videogame = Videogame.builder().name("abcdefg").build();
        VideoGameResponseDTO videoGameResponseDTO = VideoGameResponseDTO.builder().name("abcdefg").build();

        when(context.searchVideoGameByNameUseCase.execute("abcd")).thenReturn(Flux.just(videogame));
        when(context.mapper.map(any(Videogame.class), eq(VideoGameResponseDTO.class))).thenReturn(videoGameResponseDTO);

        context.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/search")
                        .queryParam("name", "abcd")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("abcdefg");
    }

    @Test
    void testUserRouteTest(){

        User user = User.builder().id(1L).username("user").build();

        when(context.getAllUserUseCase.execute()).thenReturn(Flux.just(user));

        context.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/user")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].username").isEqualTo("user");
    }

    @Test
    void testLooginRouteTest(){

        User user = User.builder().id(1L).username("user").build();
        UserLoginDTO userLoginDTO = new UserLoginDTO("user", "pass");
        UserRequest userRequest = UserRequest.builder().username("user").password("pass").build();

        when(context.findUserUseCase.execute(userRequest)).thenReturn(Mono.just(user));
        when(context.mapper.map(any(UserLoginDTO.class), eq(UserRequest.class))).thenReturn(userRequest);
        when(context.passWordEncoder.matches(userRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(context.jwtUtils.getToken(user)).thenReturn("ejWojafstejcs");

        context.webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/login")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userLoginDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isNotEmpty();
                        }
                );
    }

    @Test
    void testSignInRouteTest(){

        User user = User.builder().id(1L).username("user").build();
        UserSaveDTO userSaveDTO = new UserSaveDTO("user", "pass", false);
        UserRequest userRequest = UserRequest.builder().username("user").password("pass").build();

        when(context.createUserUseCase.execute(userRequest)).thenReturn(Mono.just(user));
        when(context.mapper.map(any(UserSaveDTO.class), eq(UserRequest.class))).thenReturn(userRequest);

        context.webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/signup")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userSaveDTO)
                .exchange()
                .expectStatus().isCreated();
    }
}
