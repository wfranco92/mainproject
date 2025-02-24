package co.com.companywf.usecase.getdeveloperbyid;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetDeveloperByIdUseCase {

    private final DeveloperRepository developerRepository;

    public Mono<Developer> execute(String id){
        return developerRepository.getDeveloperById(id);
    }
}
