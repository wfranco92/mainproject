package co.com.companywf.model.videogame;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String locationId;
    private String name;
    private LocalDateTime createdAt;
}
