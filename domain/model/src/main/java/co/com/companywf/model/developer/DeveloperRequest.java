package co.com.companywf.model.developer;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperRequest {
    private String developerId;
    private String name;
    private LocalDateTime createdAt;
}
