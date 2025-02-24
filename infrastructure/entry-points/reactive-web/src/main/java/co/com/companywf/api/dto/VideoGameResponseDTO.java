package co.com.companywf.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoGameResponseDTO {
    private String name;
    private LocalDateTime CreatedAt;
}
