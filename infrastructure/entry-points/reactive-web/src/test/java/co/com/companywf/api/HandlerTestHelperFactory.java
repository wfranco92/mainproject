package co.com.companywf.api;

import co.com.companywf.api.jwt.JwtUtils;
import co.com.companywf.usecase.createuser.CreateUserUseCase;
import co.com.companywf.usecase.deletevideogame.DeleteVideoGameUseCase;
import co.com.companywf.usecase.finduser.FindUserUseCase;
import co.com.companywf.usecase.getalldeveloper.GetAllDeveloperUseCase;
import co.com.companywf.usecase.getallgender.GetAllGenderUseCase;
import co.com.companywf.usecase.getalllocation.GetAllLocationUseCase;
import co.com.companywf.usecase.getallstatus.GetAllStatusUseCase;
import co.com.companywf.usecase.getalluser.GetAllUserUseCase;
import co.com.companywf.usecase.getallvideogames.GetAllVideoGamesUseCase;
import co.com.companywf.usecase.getdeveloperbyid.GetDeveloperByIdUseCase;
import co.com.companywf.usecase.getgenderbyid.GetGenderByIdUseCase;
import co.com.companywf.usecase.getlocationbyid.GetLocationByIdUseCase;
import co.com.companywf.usecase.getstatusbyid.GetStatusByIdUseCase;
import co.com.companywf.usecase.getvideogamebyid.GetVideoGameByIdUseCase;
import co.com.companywf.usecase.saveallvideogames.SaveAllVideoGamesUseCase;
import co.com.companywf.usecase.savedeveloper.SaveDeveloperUseCase;
import co.com.companywf.usecase.savegender.SaveGenderUseCase;
import co.com.companywf.usecase.savelocation.SaveLocationUseCase;
import co.com.companywf.usecase.savestatus.SaveStatusUseCase;
import co.com.companywf.usecase.searchvideogamebyname.SearchVideoGameByNameUseCase;
import co.com.companywf.usecase.statistics.StatisticsUseCase;
import co.com.companywf.usecase.updatedeveloper.UpdateDeveloperUseCase;
import co.com.companywf.usecase.updategender.UpdateGenderUseCase;
import co.com.companywf.usecase.updatelocation.UpdateLocationUseCase;
import co.com.companywf.usecase.updatestatus.UpdateStatusUseCase;
import co.com.companywf.usecase.updatevideogame.UpdateVideoGameUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.mockito.Mockito.mock;

public class HandlerTestHelperFactory {

    public static class HandlerTestContext {
        public final Handler handler;
        public final WebTestClient webTestClient;

        // Exponer los mocks por si querés usarlos en tus tests
        public final GetAllVideoGamesUseCase getAllVideoGamesUseCase = mock(GetAllVideoGamesUseCase.class);
        public final GetVideoGameByIdUseCase getVideoGameByIdUseCase = mock(GetVideoGameByIdUseCase.class);
        public final SaveAllVideoGamesUseCase saveAllVideoGamesUseCase = mock(SaveAllVideoGamesUseCase.class);
        public final SaveGenderUseCase saveGenderUseCase = mock(SaveGenderUseCase.class);
        public final GetGenderByIdUseCase getGenderByIdUseCase = mock(GetGenderByIdUseCase.class);
        public final GetAllGenderUseCase getAllGenderUseCase = mock(GetAllGenderUseCase.class);
        public final SaveStatusUseCase saveStatusUseCase = mock(SaveStatusUseCase.class);
        public final GetAllStatusUseCase getAllStatusUseCase = mock(GetAllStatusUseCase.class);
        public final GetStatusByIdUseCase getStatusByIdUseCase = mock(GetStatusByIdUseCase.class);
        public final SaveDeveloperUseCase saveDeveloperUseCase = mock(SaveDeveloperUseCase.class);
        public final GetAllDeveloperUseCase getAllDeveloperUseCase = mock(GetAllDeveloperUseCase.class);
        public final GetDeveloperByIdUseCase getDeveloperByIdUseCase = mock(GetDeveloperByIdUseCase.class);
        public final GetLocationByIdUseCase getLocationByIdUseCase = mock(GetLocationByIdUseCase.class);
        public final GetAllLocationUseCase getAllLocationUseCase = mock(GetAllLocationUseCase.class);
        public final SaveLocationUseCase saveLocationUseCase = mock(SaveLocationUseCase.class);
        public final UpdateGenderUseCase updateGenderUseCase = mock(UpdateGenderUseCase.class);
        public final UpdateVideoGameUseCase updateVideoGameUseCase = mock(UpdateVideoGameUseCase.class);
        public final UpdateStatusUseCase updateStatusUseCase = mock(UpdateStatusUseCase.class);
        public final UpdateDeveloperUseCase updateDeveloperUseCase = mock(UpdateDeveloperUseCase.class);
        public final UpdateLocationUseCase updateLocationUseCase = mock(UpdateLocationUseCase.class);
        public final DeleteVideoGameUseCase deleteVideoGameUseCase = mock(DeleteVideoGameUseCase.class);
        public final StatisticsUseCase statisticsUseCase = mock(StatisticsUseCase.class);
        public final SearchVideoGameByNameUseCase searchVideoGameByNameUseCase = mock(SearchVideoGameByNameUseCase.class);
        public final CreateUserUseCase createUserUseCase = mock(CreateUserUseCase.class);
        public final FindUserUseCase findUserUseCase = mock(FindUserUseCase.class);
        public final GetAllUserUseCase getAllUserUseCase = mock(GetAllUserUseCase.class);
        public final ObjectMapper mapper = mock(ObjectMapper.class);
        public final MapReactiveUserDetailsService detailsService = mock(MapReactiveUserDetailsService.class);
        public final PasswordEncoder passWordEncoder = mock(PasswordEncoder.class);
        public final JwtUtils jwtUtils = mock(JwtUtils.class);

        public HandlerTestContext() {
            this.handler = new Handler(
                    getAllVideoGamesUseCase,
                    getVideoGameByIdUseCase,
                    saveAllVideoGamesUseCase,
                    saveGenderUseCase,
                    getGenderByIdUseCase,
                    getAllGenderUseCase,
                    saveStatusUseCase,
                    getAllStatusUseCase,
                    getStatusByIdUseCase,
                    saveDeveloperUseCase,
                    getAllDeveloperUseCase,
                    getDeveloperByIdUseCase,
                    getLocationByIdUseCase,
                    getAllLocationUseCase,
                    saveLocationUseCase,
                    updateGenderUseCase,
                    updateVideoGameUseCase,
                    updateStatusUseCase,
                    updateDeveloperUseCase,
                    updateLocationUseCase,
                    deleteVideoGameUseCase,
                    statisticsUseCase,
                    searchVideoGameByNameUseCase,
                    createUserUseCase,
                    findUserUseCase,
                    getAllUserUseCase,
                    mapper,
                    detailsService,
                    passWordEncoder,
                    jwtUtils
            );

            RouterFunction<ServerResponse> routes = new RouterRest().routerFunction(handler);
            this.webTestClient = WebTestClient.bindToRouterFunction(routes).build();
        }
    }

    public static HandlerTestContext createContext() {
        return new HandlerTestContext();
    }
}