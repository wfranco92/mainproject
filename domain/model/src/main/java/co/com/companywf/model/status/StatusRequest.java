package co.com.companywf.model.status;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class StatusRequest {
    private String statusId;
    private String description;
    private LocalDateTime createdAt;
}
