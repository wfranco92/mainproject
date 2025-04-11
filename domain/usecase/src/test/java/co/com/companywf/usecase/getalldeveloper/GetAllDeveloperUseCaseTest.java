package co.com.companywf.usecase.getalldeveloper;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllDeveloperUseCaseTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private GetAllDeveloperUseCase getAllDeveloperUseCase;

   @Test
    void getAllDeveloperTest(){

       Pageable pageable = PageRequest.of(1, 10, Sort.by(Sort.Order.asc("name")));

       when(developerRepository.getAllDeveloper(pageable)).thenReturn(Flux.just(getDeveloper()));

       StepVerifier.create(getAllDeveloperUseCase.execute("1", "10"))
               .expectNextMatches(developer ->{
                   assertNotNull(developer);
                   return true;
               })
               .expectComplete()
               .verify();
   }

    private Developer getDeveloper() {
       return Developer.builder()
               .developerId("id")
               .name("name")
               .createdAt(LocalDateTime.now())
               .build();
    }

}