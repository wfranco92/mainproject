package co.com.companywf.usecase.getalldeveloper;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllDeveloperUseCase {

    private final DeveloperRepository developerRepository;

    public Flux<Developer> execute(){
        return developerRepository.getAllDeveloper();
    }
}
