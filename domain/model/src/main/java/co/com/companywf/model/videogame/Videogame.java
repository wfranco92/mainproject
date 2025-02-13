package co.com.companywf.model.videogame;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Videogame {
    private String id;
    private String name;
    private String gender;
    private String status;
    private String developer;
    private String location;
    private LocalDateTime createdAt;
}
