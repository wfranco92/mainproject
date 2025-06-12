package co.com.companywf.model.developer;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Developer {
    private String developerId;
    private String name;
    private LocalDateTime createdAt;
}
