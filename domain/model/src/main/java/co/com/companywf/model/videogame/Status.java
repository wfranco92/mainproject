package co.com.companywf.model.videogame;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    private String statusId;
    private String description;
    private LocalDateTime createdAt;
}
