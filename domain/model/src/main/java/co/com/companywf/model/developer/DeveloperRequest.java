package co.com.companywf.model.developer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
