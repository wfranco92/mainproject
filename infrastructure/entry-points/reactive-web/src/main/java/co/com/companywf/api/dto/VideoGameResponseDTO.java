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
    private String id;
    private String name;
    private String gender;
    private String status;
    private String developer;
    private String location;
    private LocalDateTime createdAt;
}
