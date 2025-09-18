package co.com.companywf.usecase.getalldeveloper;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllDeveloperUseCase {

    private final DeveloperRepository developerRepository;

    public Flux<Developer> execute(String page, String size){
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by(Sort.Order.asc("name")));
        return developerRepository.getAllDeveloper(pageable);
    }
}
