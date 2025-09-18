package co.com.companywf.jpa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VideoGameDescriptionDTO {
    private String id;
    private String name;
    private String gender;
    private String status;
    private String developer;
    private String location;
    private LocalDateTime createdAt;
}
