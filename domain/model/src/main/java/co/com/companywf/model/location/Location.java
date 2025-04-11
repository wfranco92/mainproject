package co.com.companywf.model.location;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Location {
    private String locationId;
    private String name;
    private LocalDateTime createdAt;
}
