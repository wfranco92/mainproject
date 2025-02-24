package co.com.companywf.model.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LocationRequest {
    private String locationId;
    private String name;
    private LocalDateTime createdAt;
}
